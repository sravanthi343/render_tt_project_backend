package com.cms.service;

import com.cms.dto.ComplaintRequest;
import com.cms.model.*;
import com.cms.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ComplaintService {
    @Autowired private ComplaintRepository complaintRepository;
    @Autowired private UserRepository userRepository;

    public Complaint create(ComplaintRequest req, String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Complaint c = new Complaint();
        c.setTitle(req.getTitle());
        c.setDescription(req.getDescription());
        c.setCategory(req.getCategory());
        c.setRaisedBy(userId);
        c.setRaisedByName(user.getFullName());
        c.setStatus(Complaint.Status.OPEN);
        return complaintRepository.save(c);
    }

    public List<Complaint> getAll(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (user.getRole() == User.Role.FACULTY)
            return complaintRepository.findAllByOrderByCreatedAtDesc();
        return complaintRepository.findByRaisedByOrderByCreatedAtDesc(userId);
    }

    public Complaint updateStatus(Long id, String status, String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (user.getRole() != User.Role.FACULTY)
            throw new RuntimeException("Only Faculty can update complaint status");
        Complaint c = complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));
        c.setStatus(Complaint.Status.valueOf(status.toUpperCase()));
        return complaintRepository.save(c);
    }
}
