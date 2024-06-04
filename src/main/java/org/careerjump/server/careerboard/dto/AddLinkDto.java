package org.careerjump.server.careerboard.dto;

import lombok.Data;

import java.util.List;

@Data
public class AddLinkDto {
    private List<String> linkList;
}
