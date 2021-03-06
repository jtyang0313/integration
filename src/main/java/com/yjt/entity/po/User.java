package com.yjt.entity.po;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import lombok.Data;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("tb_user")
@Data
public class User implements UserDetails{
	
	private static final long serialVersionUID = 1L;

	@TableId(type= IdType.AUTO)
	private Integer id;

    private String companyCode;

    private String userName;

    private String password;

    private String realName;

    private Date createTime;

    private Date updateTime;

    @TableField(exist = false)
	private List<Permission> permissions;
    
    @TableField(exist = false)
	private List<Integer> roleIds;
    
    @TableField(exist = false)
    private String roleName;
    
    @TableField(exist = false)
    private String companyName;
    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<GrantedAuthority> auths = new ArrayList<>();
		List<Permission> permissions = this.getPermissions();
		for (Permission permission : permissions)
		{
			if(permission!=null)
			{
				auths.add(new SimpleGrantedAuthority(permission.getPermissionCode()));
			}
		}
		return auths;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
