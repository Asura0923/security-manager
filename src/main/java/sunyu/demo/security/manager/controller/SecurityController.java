package sunyu.demo.security.manager.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sunyu.demo.security.manager.config.security.SecurityInfo;
import sunyu.demo.security.manager.mapper.SecurityGroupMapper;
import sunyu.demo.security.manager.mapper.SecurityResourceMapper;
import sunyu.demo.security.manager.mapper.SecurityRoleMapper;
import sunyu.demo.security.manager.pojo.*;
import sunyu.demo.security.manager.service.SecurityGroupService;
import sunyu.demo.security.manager.service.SecurityResourceService;
import sunyu.demo.security.manager.service.SecurityRoleService;
import sunyu.demo.security.manager.service.SecurityUserService;
import sunyu.demo.security.manager.util.IdUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * shiro controller
 *
 * @author SunYu
 */
@Controller
@RequestMapping("/security")
public class SecurityController {
    @Autowired
    SecurityResourceService securityResourceService;
    @Autowired
    SecurityResourceMapper securityResourceMapper;
    @Autowired
    SecurityUserService securityUserService;
    @Autowired
    SecurityGroupService securityGroupService;
    @Autowired
    SecurityGroupMapper securityGroupMapper;
    @Autowired
    SecurityRoleService securityRoleService;
    @Autowired
    SecurityRoleMapper securityRoleMapper;
    @Autowired
    IdUtil idUtil;
    @Autowired
    PasswordEncoder passwordEncoder;

    private static final String ADD = "add";
    private static final String UPDATE = "update";
    private static final String EDIT = "edit";
    private static final String ERROR = "error";
    private static final String MESSAGE = "message";

    @RequestMapping("/login")
    public ModelAndView login(@RequestParam(name = ERROR, required = false) String error
            , @SessionAttribute(name = WebAttributes.AUTHENTICATION_EXCEPTION, required = false) AuthenticationException exception
            , Model model) {
        if (error != null) {
            model.addAttribute(ERROR, exception.getMessage());
        }
        return new ModelAndView("login", model.asMap());
    }

    @RequestMapping("/dashboard")
    public ModelAndView dashboard() {
        return new ModelAndView("dashboard/dashboard");
    }

    @GetMapping("/resource/list")
    public ModelAndView resource_list(Model model) {
        return new ModelAndView("resource/list", model.asMap());
    }

    @GetMapping("/group/list")
    public ModelAndView group_list(Model model) {
        return new ModelAndView("group/list", model.asMap());
    }

    @GetMapping("/user/list")
    public ModelAndView user_list(Model model, SecurityUser securityUser) {
        model.addAttribute("securityUser", securityUser);
        return new ModelAndView("user/list", model.asMap());
    }

    @GetMapping("/role/list")
    public ModelAndView role_list(Model model, SecurityRole securityRole) {
        model.addAttribute("securityRole", securityRole);
        return new ModelAndView("role/list", model.asMap());
    }

    @GetMapping("/user/resource")
    public ModelAndView user_resource(Model model, Long id) {
        SecurityUser securityUser = securityUserService.getById(id);
        model.addAttribute("securityUser", securityUser);
        List<SecurityResource> resourceList = securityResourceMapper.getResourceByUserJoinResource(securityUser);
        addResourceIdsToModel(model, resourceList);
        return new ModelAndView("user/resource", model.asMap());
    }

    @GetMapping("/user/group")
    public ModelAndView user_group(Model model, Long id) {
        SecurityUser securityUser = securityUserService.getById(id);
        model.addAttribute("securityUser", securityUser);
        List<SecurityGroup> groupList = securityGroupMapper.getGroupByUserJoinGroup(securityUser);
        if (CollUtil.isNotEmpty(groupList)) {
            List<Long> groupIds = new ArrayList<>();
            for (SecurityGroup group : groupList) {
                groupIds.add(group.getId());
            }
            model.addAttribute("groupIds", StrUtil.join(",", groupIds.toArray()));
        } else {
            model.addAttribute("groupIds", "");
        }
        return new ModelAndView("user/group", model.asMap());
    }

    @GetMapping("/user/role")
    public ModelAndView user_role(Model model, Long id) {
        SecurityUser securityUser = securityUserService.getById(id);
        model.addAttribute("securityUser", securityUser);
        List<SecurityRole> roleList = securityRoleMapper.getRoleByUserJoinRole(securityUser);
        if (CollUtil.isNotEmpty(roleList)) {
            List<Long> roleIds = new ArrayList<>();
            for (SecurityRole role : roleList) {
                roleIds.add(role.getId());
            }
            model.addAttribute("roleIds", StrUtil.join(",", roleIds.toArray()));
        } else {
            model.addAttribute("roleIds", "");
        }
        return new ModelAndView("user/role", model.asMap());
    }

    @GetMapping("/group/resource")
    public ModelAndView group_resource(Model model, Long id) {
        SecurityGroup securityGroup = securityGroupService.getById(id);
        model.addAttribute("securityGroup", securityGroup);
        List<SecurityResource> resourceList = securityResourceMapper.getResourceByGroupJoinResource(securityGroup);
        addResourceIdsToModel(model, resourceList);
        return new ModelAndView("group/resource", model.asMap());
    }

    @GetMapping("/role/resource")
    public ModelAndView role_resource(Model model, Long id) {
        SecurityRole securityRole = securityRoleService.getById(id);
        model.addAttribute("securityRole", securityRole);
        List<SecurityResource> resourceList = securityResourceMapper.getResourceByRoleJoinResource(securityRole);
        addResourceIdsToModel(model, resourceList);
        return new ModelAndView("role/resource", model.asMap());
    }

    private void addResourceIdsToModel(Model model, List<SecurityResource> resourceList) {
        if (CollUtil.isNotEmpty(resourceList)) {
            List<Long> resourceIds = new ArrayList<>();
            for (SecurityResource resource : resourceList) {
                resourceIds.add(resource.getId());
            }
            model.addAttribute("resourceIds", StrUtil.join(",", resourceIds.toArray()));
        } else {
            model.addAttribute("resourceIds", "");
        }
    }

    @PostMapping("/user/resource")
    public ModelAndView user_resource(Model model
            , @RequestParam(required = true) Long id
            , String resourceIds) {
        securityUserService.updateUserResourceJoin(id, resourceIds);
        model.addAttribute(MESSAGE, "用户资源关联关系修改成功！");
        return new ModelAndView("redirect:/security/user/list", model.asMap());
    }

    @PostMapping("/user/group")
    public ModelAndView user_group(Model model
            , @RequestParam(required = true) Long id
            , String groupIds) {
        securityUserService.updateUserGroupJoin(id, groupIds);
        model.addAttribute(MESSAGE, "用户组织关联关系修改成功！");
        return new ModelAndView("redirect:/security/user/list", model.asMap());
    }

    @PostMapping("/user/role")
    public ModelAndView user_role(Model model
            , @RequestParam(required = true) Long id
            , String roleIds) {
        securityUserService.updateUserRoleJoin(id, roleIds);
        model.addAttribute(MESSAGE, "用户角色关联关系修改成功！");
        return new ModelAndView("redirect:/security/user/list", model.asMap());
    }

    @PostMapping("/group/resource")
    public ModelAndView group_resource(Model model
            , @RequestParam(required = true) Long id
            , String resourceIds) {
        securityGroupService.updateGroupResourceJoin(id, resourceIds);
        model.addAttribute(MESSAGE, "组织资源关联关系修改成功！");
        return new ModelAndView("redirect:/security/group/list", model.asMap());
    }

    @PostMapping("/role/resource")
    public ModelAndView role_resource(Model model
            , @RequestParam(required = true) Long id
            , String resourceIds) {
        securityRoleService.updateRoleResourceJoin(id, resourceIds);
        model.addAttribute(MESSAGE, "角色资源关联关系修改成功！");
        return new ModelAndView("redirect:/security/role/list", model.asMap());
    }

    @PostMapping("/resource/list")
    @ResponseBody
    public DataTable resource_list(DataTable dataTable) {
        IPage<SecurityResource> page = new Page<>(dataTable.getPageNum(), dataTable.getLength());
        securityResourceService.page(page);
        dataTable.setDatas(securityResourceService.flatTreeList(securityResourceService.convertTreeListAddNamePre(page.getRecords(), false, null, null)));
        dataTable.setRecordsTotal(page.getTotal());
        return dataTable;
    }

    @PostMapping("/group/list")
    @ResponseBody
    public DataTable group_list(DataTable dataTable) {
        IPage<SecurityGroup> page = new Page<>(dataTable.getPageNum(), dataTable.getLength());
        securityGroupService.page(page);
        dataTable.setDatas(securityGroupService.flatTreeList(securityGroupService.convertTreeListAddNamePre(page.getRecords(), null, null)));
        dataTable.setRecordsTotal(page.getTotal());
        return dataTable;
    }

    @PostMapping("/user/list")
    @ResponseBody
    public DataTable user_list(DataTable dataTable, SecurityUser securityUser) {
        IPage<SecurityUser> page = new Page<>(dataTable.getPageNum(), dataTable.getLength());
        LambdaQueryWrapper<SecurityUser> queryWrappers = Wrappers.lambdaQuery();
        queryWrappers.orderByDesc(SecurityUser::getLastLogin);
        if (StrUtil.isNotBlank(securityUser.getName())) {
            queryWrappers.like(SecurityUser::getName, securityUser.getName());
        }
        if (StrUtil.isNotBlank(securityUser.getEmail())) {
            queryWrappers.like(SecurityUser::getEmail, securityUser.getEmail());
        }
        if (securityUser.getEnabled() != null) {
            queryWrappers.eq(SecurityUser::getEnabled, securityUser.getEnabled());
        }
        securityUserService.page(page, queryWrappers);
        dataTable.setDatas(page.getRecords());
        dataTable.setRecordsTotal(page.getTotal());
        return dataTable;
    }

    @PostMapping("/role/list")
    @ResponseBody
    public DataTable role_list(DataTable dataTable, SecurityRole securityRole) {
        IPage<SecurityRole> page = new Page<>(dataTable.getPageNum(), dataTable.getLength());
        LambdaQueryWrapper<SecurityRole> queryWrappers = Wrappers.lambdaQuery();
        if (StrUtil.isNotBlank(securityRole.getName())) {
            queryWrappers.like(SecurityRole::getName, securityRole.getName());
        }
        securityRoleService.page(page, queryWrappers);
        dataTable.setDatas(page.getRecords());
        dataTable.setRecordsTotal(page.getTotal());
        return dataTable;
    }

    @GetMapping(value = {"/resource/add", "/resource/update"})
    public ModelAndView resource_edit(Model model, SecurityResource securityResource) {
        if (securityResource.getId() == null) {
            securityResource.setId(idUtil.getId());
            securityResource.setIntercept(0);
            securityResource.setMenu(1);
            model.addAttribute(EDIT, ADD);
            model.addAttribute("securityResource", securityResource);
        } else {
            model.addAttribute(EDIT, UPDATE);
            model.addAttribute("securityResource", securityResourceService.getById(securityResource.getId()));
        }
        List<SecurityResource> resources = securityResourceService.list();
        List<SecurityResource> resourcesList = securityResourceService.convertTreeListAddNamePre(resources, false, null, null);
        List<SecurityResource> menu = securityResourceService.flatTreeList(resourcesList);
        model.addAttribute("menu", menu);
        return new ModelAndView("resource/edit", model.asMap());
    }

    @GetMapping(value = {"/group/add", "/group/update"})
    public ModelAndView group_edit(Model model, SecurityGroup securityGroup) {
        if (securityGroup.getId() == null) {
            securityGroup.setId(idUtil.getId());
            model.addAttribute(EDIT, ADD);
            model.addAttribute("securityGroup", securityGroup);
        } else {
            model.addAttribute(EDIT, UPDATE);
            model.addAttribute("securityGroup", securityGroupService.getById(securityGroup.getId()));
        }
        List<SecurityGroup> groups = securityGroupService.list();
        List<SecurityGroup> resourcesList = securityGroupService.convertTreeListAddNamePre(groups, null, null);
        List<SecurityGroup> group = securityGroupService.flatTreeList(resourcesList);
        model.addAttribute("group", group);
        return new ModelAndView("group/edit", model.asMap());
    }

    @GetMapping(value = {"/user/add", "/user/update"})
    public ModelAndView user_edit(Model model, SecurityUser securityUser) {
        if (securityUser.getId() == null) {
            securityUser.setId(idUtil.getId());
            securityUser.setEnabled(1);
            model.addAttribute(EDIT, ADD);
            model.addAttribute("securityUser", securityUser);
        } else {
            securityUser = securityUserService.getById(securityUser.getId());
            securityUser.setPassword(null);
            model.addAttribute(EDIT, UPDATE);
            model.addAttribute("securityUser", securityUser);
        }
        return new ModelAndView("user/edit", model.asMap());
    }

    @GetMapping(value = {"/role/add", "/role/update"})
    public ModelAndView role_edit(Model model, SecurityRole securityRole) {
        if (securityRole.getId() == null) {
            securityRole.setId(idUtil.getId());
            model.addAttribute(EDIT, ADD);
            model.addAttribute("securityRole", securityRole);
        } else {
            securityRole = securityRoleService.getById(securityRole.getId());
            model.addAttribute(EDIT, UPDATE);
            model.addAttribute("securityRole", securityRole);
        }
        return new ModelAndView("role/edit", model.asMap());
    }

    @PostMapping(value = {"/resource/add", "/resource/update"})
    public ModelAndView resource_edit(Model model
            , @RequestParam(required = true) String edit
            , @RequestParam(required = true) Long id
            , @RequestParam(required = true) String name
            , String uri
            , Long pid
            , @RequestParam(required = true) Integer seq
            , @RequestParam(required = true) Integer intercept
            , @RequestParam(required = true) Integer menu
            , String icon) {
        SecurityResource resource = new SecurityResource();
        resource.setId(id);
        resource.setName(name);
        resource.setUri(uri);
        resource.setPid(pid);
        resource.setSeq(seq);
        resource.setIntercept(intercept);
        resource.setMenu(menu);
        resource.setIcon(icon);
        if (edit.equals(ADD)) {
            securityResourceService.save(resource);
            model.addAttribute(MESSAGE, "资源 {name} 添加成功！".replace("{name}", resource.getName()));
        } else if (edit.equals(UPDATE)) {
            securityResourceService.updateById(resource);
            model.addAttribute(MESSAGE, "资源 {name} 修改成功！".replace("{name}", resource.getName()));
        }
        SecurityInfo securityInfo = (SecurityInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        securityResourceService.refreshMenu(securityInfo);
        return new ModelAndView("redirect:/security/resource/list", model.asMap());
    }

    @PostMapping(value = {"/group/add", "/group/update"})
    public ModelAndView group_edit(Model model
            , @RequestParam(required = true) String edit
            , @RequestParam(required = true) Long id
            , @RequestParam(required = true) String name
            , Long pid
            , @RequestParam(required = true) Integer seq) {
        SecurityGroup group = new SecurityGroup();
        group.setId(id);
        group.setName(name);
        group.setPid(pid);
        group.setSeq(seq);
        if (edit.equals(ADD)) {
            securityGroupService.save(group);
            model.addAttribute(MESSAGE, "组织 {name} 添加成功！".replace("{name}", group.getName()));
        } else if (edit.equals(UPDATE)) {
            securityGroupService.updateById(group);
            model.addAttribute(MESSAGE, "组织 {name} 修改成功！".replace("{name}", group.getName()));
        }
        return new ModelAndView("redirect:/security/group/list", model.asMap());
    }

    @PostMapping(value = {"/role/add", "/role/update"})
    public ModelAndView role_edit(Model model
            , @RequestParam(required = true) String edit
            , @RequestParam(required = true) Long id
            , @RequestParam(required = true) String name) {
        SecurityRole role = new SecurityRole();
        role.setId(id);
        role.setName(name);
        if (edit.equals(ADD)) {
            securityRoleService.save(role);
            model.addAttribute(MESSAGE, "角色 {name} 添加成功！".replace("{name}", role.getName()));
        } else if (edit.equals(UPDATE)) {
            securityRoleService.updateById(role);
            model.addAttribute(MESSAGE, "角色 {name} 修改成功！".replace("{name}", role.getName()));
        }
        return new ModelAndView("redirect:/security/role/list", model.asMap());
    }

    @PostMapping(value = {"/user/add", "/user/update"})
    public ModelAndView user_edit(Model model
            , @RequestParam(required = true) String edit
            , @RequestParam(required = true) Long id
            , @RequestParam(required = true) String name
            , @RequestParam(required = true) String email
            , String password
            , @RequestParam(required = true) Integer enabled) {
        SecurityUser user = new SecurityUser();
        user.setId(id);
        user.setName(name);
        user.setEmail(email);
        user.setEnabled(enabled);
        if (edit.equals(ADD)) {
            if (securityUserService.count(Wrappers.<SecurityUser>lambdaQuery().eq(SecurityUser::getEmail, user.getEmail())) > 0) {
                model.addAttribute("error", "用户 {name} 添加失败！Email {email} 已注册！".replace("{name}", name).replace("{email}", email));
            } else {
                user.setPassword(passwordEncoder.encode(password));
                securityUserService.save(user);
                model.addAttribute(MESSAGE, "用户 {name} 添加成功！".replace("{name}", name));
            }
        } else if (edit.equals(UPDATE)) {
            SecurityUser t = securityUserService.getOne(Wrappers.<SecurityUser>lambdaQuery().eq(SecurityUser::getEmail, email));
            SecurityUser oldUser = securityUserService.getById(id);
            if (t != null && t.getId().longValue() != id.longValue()) {
                model.addAttribute("error", "用户 {name} 修改失败！Email {email} 已注册！".replace("{name}", name).replace("{email}", t.getEmail()));
            } else {
                if (StrUtil.isBlank(password)) {
                    oldUser.setName(name);
                    oldUser.setEmail(email);
                    oldUser.setEnabled(enabled);
                    securityUserService.updateById(oldUser);
                } else {
                    user.setPassword(passwordEncoder.encode(password));
                    securityUserService.updateById(user);
                }
                model.addAttribute(MESSAGE, "用户 {name} 修改成功！".replace("{name}", user.getName()));
            }
        }
        return new ModelAndView("redirect:/security/user/list", model.asMap());
    }

    @PostMapping("/resource/delete")
    @ResponseBody
    public ControllerResult resource_delete(@RequestParam(required = true) Long id) {
        SecurityResource resource = securityResourceService.getById(id);
        ControllerResult result = new ControllerResult();
        securityResourceService.deleteResourceJoin(id);
        SecurityInfo securityInfo = (SecurityInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        securityResourceService.refreshMenu(securityInfo);
        result.setMessage("资源 {name} 删除成功！".replace("{name}", resource.getName()));
        return result;
    }

    @PostMapping("/user/delete")
    @ResponseBody
    public ControllerResult user_delete(@RequestParam(required = true) Long id) {
        SecurityUser user = securityUserService.getById(id);
        ControllerResult result = new ControllerResult();
        securityUserService.deleteUserJoin(id);
        result.setMessage("用户 {name} 删除成功！".replace("{name}", user.getName()));
        return result;
    }

    @PostMapping("/role/delete")
    @ResponseBody
    public ControllerResult role_delete(@RequestParam(required = true) Long id) {
        SecurityRole role = securityRoleService.getById(id);
        ControllerResult result = new ControllerResult();
        securityRoleService.deleteRoleJoin(id);
        result.setMessage("角色 {name} 删除成功！".replace("{name}", role.getName()));
        return result;
    }

    @PostMapping("/group/delete")
    @ResponseBody
    public ControllerResult group_delete(@RequestParam(required = true) Long id) {
        SecurityGroup group = securityGroupService.getById(id);
        ControllerResult result = new ControllerResult();
        securityGroupService.deleteGroupJoin(id);
        result.setMessage("组织 {name} 删除成功！".replace("{name}", group.getName()));
        return result;
    }

}
