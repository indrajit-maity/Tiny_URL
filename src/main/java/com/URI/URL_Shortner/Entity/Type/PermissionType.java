package com.URI.URL_Shortner.Entity.Type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PermissionType {
    MALWARE_UPDATE("malware:check"),
    USER_REMOVE("user remove");
    private  final String permission;
}
