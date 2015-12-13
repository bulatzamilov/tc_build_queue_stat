package ru.innopolis.aj.prj;
 
import jetbrains.buildServer.controllers.BaseController;
import jetbrains.buildServer.web.openapi.PluginDescriptor;
import jetbrains.buildServer.web.openapi.WebControllerManager;
//import org.hsqldb.lib.HashMap;
import java.util.HashMap;
import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.web.servlet.ModelAndView;
import jetbrains.buildServer.users.SUser;
import jetbrains.buildServer.web.util.SessionUser;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class AppServer extends BaseController {
    private PluginDescriptor myDescriptor;
    private TeamCityBuildsListener myBuildListener;

    public AppServer (WebControllerManager manager,
                      PluginDescriptor descriptor,
                      TeamCityBuildsListener listener) {
        manager.registerController("/QueueStat.html",this);
        myDescriptor = descriptor;
        myBuildListener = listener;
    }
 
    @Nullable
    @Override
    protected ModelAndView doHandle(@NotNull HttpServletRequest httpServletRequest, @NotNull HttpServletResponse httpServletResponse) throws Exception {
        SUser user = SessionUser.getUser(httpServletRequest);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userName", user.getDescriptiveName());
        params.put("messages", myBuildListener.getMessages());

        return new ModelAndView(myDescriptor.getPluginResourcesPath("QueueStat.jsp"), params);
    }


}