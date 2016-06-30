package com.docdoku.api;


import com.docdoku.api.client.ApiException;
import com.docdoku.api.models.WorkspaceDTO;
import com.docdoku.api.models.WorkspaceListDTO;
import com.docdoku.api.services.WorkspacesApi;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.UUID;

@RunWith(JUnit4.class)
public class WorkspacesApiTest {

    @Test
    public void createWorkspaceTest() throws ApiException {
        WorkspaceDTO workspace = new WorkspaceDTO();
        String workspaceId = UUID.randomUUID().toString().substring(0,6);
        workspace.setId(workspaceId);
        workspace.setDescription("Generated by tests");
        workspace.setFolderLocked(false);
        WorkspaceDTO createdWorkspace = new WorkspacesApi(TestConfig.BASIC_CLIENT).createWorkspace(workspace, TestConfig.LOGIN);
        Assert.assertEquals(workspace,createdWorkspace);
    }

    @Test
    public void getWorkspaceList() throws ApiException {
        WorkspaceDTO workspace = new WorkspaceDTO();
        String workspaceId = UUID.randomUUID().toString().substring(0,6);
        workspace.setId(workspaceId);
        workspace.setDescription("Generated by tests");
        workspace.setFolderLocked(false);
        WorkspacesApi workspacesApi = new WorkspacesApi(TestConfig.BASIC_CLIENT);
        WorkspaceDTO createdWorkspace = workspacesApi.createWorkspace(workspace, TestConfig.LOGIN);
        WorkspaceListDTO workspacesForConnectedUser = workspacesApi.getWorkspacesForConnectedUser();
        Assert.assertTrue(workspacesForConnectedUser.getAllWorkspaces().contains(createdWorkspace));
    }

    @Test
    public void updateWorkspace() throws ApiException {
        WorkspaceDTO workspace = new WorkspaceDTO();
        String workspaceId = UUID.randomUUID().toString().substring(0,6);
        workspace.setId(workspaceId);
        workspace.setDescription("Generated by tests");
        workspace.setFolderLocked(false);
        WorkspacesApi workspacesApi = new WorkspacesApi(TestConfig.BASIC_CLIENT);
        WorkspaceDTO createdWorkspace = workspacesApi.createWorkspace(workspace, TestConfig.LOGIN);
        String newDescription = "Updated by tests";
        createdWorkspace.setDescription(newDescription);
        WorkspaceDTO updatedWorkspace = workspacesApi.updateWorkspace(workspaceId, createdWorkspace);
        Assert.assertEquals(updatedWorkspace.getDescription(),newDescription);
        Assert.assertEquals(updatedWorkspace,createdWorkspace);
    }

}
