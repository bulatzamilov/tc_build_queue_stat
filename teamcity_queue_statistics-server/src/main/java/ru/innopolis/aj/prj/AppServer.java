package ru.innopolis.aj.prj;
 
import jetbrains.buildServer.controllers.BaseController;
import jetbrains.buildServer.web.openapi.PluginDescriptor;
import jetbrains.buildServer.web.openapi.WebControllerManager;
import org.jetbrains.annotations.Nullable;
import org.springframework.web.servlet.ModelAndView;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class AppServer extends BaseController {
    private PluginDescriptor myDescriptor;
 
    public AppServer (WebControllerManager manager, PluginDescriptor descriptor) {
        manager.registerController("/QueueStat.html",this);
        myDescriptor=descriptor;
    }
 
    @Nullable
    @Override
    protected ModelAndView doHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        return new ModelAndView(myDescriptor.getPluginResourcesPath("QueueStat.jsp"));
    }
}