package org.dsmhack.service;

import org.dsmhack.model.ReportData;
import org.dsmhack.model.ReportOrganization;
import org.dsmhack.model.ReportProject;
import org.dsmhack.model.ReportUser;
import org.dsmhack.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ReportService {
    @Autowired
    private ReportRepository reportRepository;

    public ReportOrganization getReportOrganization(String organizationId) {
        List<ReportData> reportDatas = reportRepository.findAllReportingInformation(organizationId);

        ReportOrganization reportOrganization = new ReportOrganization();
        List<ReportProject> organizationProjects = new ArrayList<ReportProject>();
        List<ReportUser> projectUsers = buildReportOrganizationSkeleton(reportDatas).getUsers();

        double organizationTotalHours = 0;
        for (ReportData reportData : reportDatas) {
            double totalHours = calculateHours(reportData.getTimeIn(), reportData.getTimeOut());
            if (addUser(reportData.getUserGuid(), projectUsers)) {
                ReportUser reportUser = new ReportUser();
                reportUser.setUserGuid(reportData.getUserGuid());
                reportUser.setFirstName(reportData.getFirstName());
                reportUser.setLastName(reportData.getLastName());
                reportUser.setTotalHours(totalHours);

                ReportProject userProject = new ReportProject();
                userProject.setProjectGuid(reportData.getProjectGuid());
                userProject.setName(reportData.getProjectName());
                userProject.setTotalHours(totalHours);
                reportUser.setProjects(Arrays.asList(userProject));

                projectUsers.add(reportUser);
            } else {
                ReportUser reportUser = findUser(reportData.getUserGuid(), projectUsers);

                if (addProject(reportData.getProjectGuid(), reportUser.getProjects())) {
                    ReportProject userProject = new ReportProject();
                    userProject.setProjectGuid(reportData.getProjectGuid());
                    userProject.setName(reportData.getProjectName());

                    userProject.setTotalHours(totalHours);
                    List<ReportProject> reportProjects = new ArrayList<ReportProject>();
                    reportProjects.addAll(reportUser.getProjects());
                    reportProjects.add(userProject);
                    reportUser.setProjects(reportProjects);
                } else {
                    ReportProject reportProject = findReportProject(reportData.getProjectGuid(), reportUser.getProjects());
                    reportProject.setTotalHours(reportProject.getTotalHours() + totalHours);
                }
                reportUser.setTotalHours(reportUser.getTotalHours() + totalHours);
            }

            if (addProject(reportData.getProjectGuid(), organizationProjects)) {
                ReportProject organizationProject = new ReportProject();
                organizationProject.setProjectGuid(reportData.getProjectGuid());
                organizationProject.setName(reportData.getProjectName());
                organizationProject.setTotalHours(totalHours);
                organizationProjects.add(organizationProject);
            } else {
                ReportProject reportProject = findReportProject(reportData.getProjectGuid(), organizationProjects);
                reportProject.setTotalHours(reportProject.getTotalHours() + totalHours);
            }
            organizationTotalHours += totalHours;
        }
        reportOrganization.setProjects(organizationProjects);
        reportOrganization.setUsers(projectUsers);
        reportOrganization.setTotalHours(organizationTotalHours);
        return reportOrganization;
    }

    public ReportOrganization buildReportOrganizationSkeleton(List<ReportData> reportDatas) {
        List<ReportProject> uniqueProjects = buildUniqueProjects(reportDatas);
        List<ReportUser> uniqueUsers = buildUniqueUsers(reportDatas);
        for (ReportUser uniqueUser : uniqueUsers) {
            List<ReportProject> projects = new ArrayList<ReportProject>();
            for (ReportProject uniqueProject : uniqueProjects) {
                ReportProject project = new ReportProject();
                project.setProjectGuid(uniqueProject.getProjectGuid());
                project.setName(uniqueProject.getName());
                projects.add(project);
            }
            uniqueUser.setProjects(projects);
        }
        ReportOrganization reportOrganization = new ReportOrganization();
        reportOrganization.setProjects(uniqueProjects);
        reportOrganization.setUsers(uniqueUsers);
        return reportOrganization;
    }

    private List<ReportProject> buildUniqueProjects(List<ReportData> reportDatas) {
        List<ReportProject> uniqueProjects = new ArrayList<ReportProject>();
        for (ReportData reportData : reportDatas) {
            if (!projectGuidExists(reportData.getProjectGuid(), uniqueProjects)) {
                ReportProject project = new ReportProject();
                project.setProjectGuid(reportData.getProjectGuid());
                project.setName(reportData.getProjectName());
                uniqueProjects.add(project);
            }
        }
        return uniqueProjects;
    }

    private List<ReportUser> buildUniqueUsers(List<ReportData> reportDatas) {
        List<ReportUser> uniqueUsers = new ArrayList<ReportUser>();
        for (ReportData reportData : reportDatas) {
            if (!userGuidExists(reportData.getUserGuid(), uniqueUsers)) {
                ReportUser user = new ReportUser();
                user.setUserGuid(reportData.getUserGuid());
                user.setFirstName(reportData.getFirstName());
                user.setLastName(reportData.getLastName());
                uniqueUsers.add(user);
            }
        }
        return uniqueUsers;
    }

    private boolean addUser(String userGuid, List<ReportUser> projectUsers) {
        return !userGuidExists(userGuid, projectUsers);
    }

    private boolean addProject(String projectGuid, List<ReportProject> projects) {
        return !projectGuidExists(projectGuid, projects);
    }

    ReportUser findUser(String guid, List<ReportUser> users) {
        for (ReportUser user : users) {
            if (user.getUserGuid().equals(guid)) {
                return user;
            }
        }
        return new ReportUser();
    }

    boolean projectGuidExists(String guid, List<ReportProject> projects) {
        for (ReportProject project : projects) {
            if (project.getProjectGuid().equals(guid)) {
                return true;
            }
        }
        return false;
    }

    ReportProject findReportProject(String guid, List<ReportProject> projects) {
        for (ReportProject project: projects) {
            if (project.getProjectGuid().equals(guid)) {
                return project;
            }
        }
        return new ReportProject();
    }

    boolean userGuidExists(String guid, List<ReportUser> users) {
        for (ReportUser user : users) {
            if (user.getUserGuid().equals(guid)) {
                return true;
            }
        }
        return false;
    }

    double calculateHours(Timestamp checkIn, Timestamp checkOut) {
        if (checkOut == null) {
            return 0;
        }
        return (double) checkIn.toLocalDateTime().until(checkOut.toLocalDateTime(), ChronoUnit.MINUTES) / 60;
    }
}
