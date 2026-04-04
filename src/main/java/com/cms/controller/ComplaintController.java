package com.cms.controller;

import com.cms.dto.*;
import com.cms.model.Complaint;
import com.cms.service.ComplaintService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {
    @Autowired private ComplaintService complaintService;

    @PostMapping
    public ResponseEntity<ApiResponse<Complaint>> create(
            @Valid @RequestBody ComplaintRequest req, Authentication auth) {
        return ResponseEntity.ok(ApiResponse.success("Complaint submitted", complaintService.create(req, auth.getName())));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Complaint>>> getAll(Authentication auth) {
        return ResponseEntity.ok(ApiResponse.success("OK", complaintService.getAll(auth.getName())));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ApiResponse<Complaint>> updateStatus(
            @PathVariable Long id, @RequestParam String status, Authentication auth) {
        return ResponseEntity.ok(ApiResponse.success("Updated", complaintService.updateStatus(id, status, auth.getName())));
    }
}
