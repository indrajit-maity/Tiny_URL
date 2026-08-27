package com.URI.URL_Shortner.Security;

import com.URI.URL_Shortner.Entity.Type.PermissionType;
import com.URI.URL_Shortner.Entity.Type.RoleType;
import com.URI.URL_Shortner.Entity.Type.RoleType.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class RolePermissionMapping {

    private  static  final Map<RoleType, Set<PermissionType>> map=Map.of(
            RoleType.USER, Set.of(PermissionType.MALWARE_UPDATE),
            RoleType.ADMIN, Set.of(PermissionType.MALWARE_UPDATE,PermissionType.USER_REMOVE)
    );
    public static Set<SimpleGrantedAuthority> getAuthoritiesForRole(RoleType roleType){
        return map.get(roleType).stream()
                .map(permission->new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
