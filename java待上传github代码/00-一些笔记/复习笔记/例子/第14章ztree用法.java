
@Namespace("/sysadmin")
@Results({ @Result(name = "list", location = "/WEB-INF/pages/sysadmin/role/jRoleList.jsp"),
		@Result(name = "list1", location = "roleAction_list", type = "redirectAction") })
public class RoleAction extends BaseAction<Role> implements ModelDriven<Role> {
	@Autowired
	private RoleService ds;
	@Autowired
	private ModuleService ms;
	private String moduleIds;

	public String getModuleIds() {
		return moduleIds;
	}

	public void setModuleIds(String moduleIds) {
		this.moduleIds = moduleIds;
	}

	private Role d = new Role();


	@Action(value = "roleAction_ajax")
	public String ajax() throws IOException {
		Set<Module> mm = ds.get(d.getId()).getModules();
		List<Module> find = ms.find(null);
		// { id:1, pId:0, name:"随意勾选 1"},
		List l = new ArrayList();
		for (Module mo : find) {
			Map m = new HashMap();
			m.put("id", mo.getId());
			m.put("pId", mo.getParentId());
			m.put("name", mo.getName());
			if (mm.contains(mo)) {
				m.put("checked", "true");
			}
			l.add(m);
		}
		HttpServletResponse r = ServletActionContext.getResponse();
		String s = JSON.toJSONString(l);
		r.setCharacterEncoding("utf-8");
		r.getWriter().write(s);
		return null;
	}


	@Override
	public Role getModel() {
		return d;
	}

}


前端把ztree勾选的数据传给后台的主要代码：

		function submitCheckedNodes(){
		 	zTree = $.fn.zTree.getZTreeObj("treeDemo");
			s="";
			xs=zTree.getCheckedNodes();
			for(i=0;i<xs.length;i++){
				s=s+xs[i].id+",";
			}
			alert(s);
			$("#moduleIds").val(s);
		}