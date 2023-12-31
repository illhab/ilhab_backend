package com.illhab.illhabServer.controller;

import com.illhab.illhabServer.dto.ProjectDto;
import com.illhab.illhabServer.dto.UserProjectDto;
import com.illhab.illhabServer.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/projects")
    public ResponseEntity<ProjectDto.CreateResponse> create(
        @RequestBody ProjectDto.CreateRequest projectDto) {
        return ResponseEntity.ok().body(projectService.create(projectDto));
    }

    @PostMapping("/projects/{projectId}/{userId}")
    public ResponseEntity<UserProjectDto.Response> join(@PathVariable Long projectId,
        @PathVariable Long userId) {
        return ResponseEntity.ok().body(projectService.join(projectId, userId));
    }

    @DeleteMapping("/projects/{projectId}/{userId}")
    public ResponseEntity<UserProjectDto.Response> ban(@PathVariable Long projectId,
        @PathVariable Long userId) {
        return ResponseEntity.ok().body(projectService.ban(projectId, userId));
    }
}
