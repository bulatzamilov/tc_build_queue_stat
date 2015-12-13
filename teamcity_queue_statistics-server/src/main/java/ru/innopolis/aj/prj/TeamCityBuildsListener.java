package ru.innopolis.aj.prj;

import jetbrains.buildServer.serverSide.BuildServerAdapter;
import jetbrains.buildServer.serverSide.SBuildServer;
import jetbrains.buildServer.serverSide.SQueuedBuild;
import org.jetbrains.annotations.NotNull;
import java.text.DateFormat;
import java.util.*;
import jetbrains.buildServer.serverSide.*;

/**
 * Listen to builds related events
 */
public class TeamCityBuildsListener extends BuildServerAdapter{

    private final List<String> myLog = new ArrayList<String>();
    private Map<String, List<String>> myConfigurationLog = new HashMap<String, List<String>>();
    private SBuildServer myBuildServer;

    public TeamCityBuildsListener(SBuildServer sBuildServer){
        myBuildServer = sBuildServer;
    }

    public void register() {
        myBuildServer.addListener(this);
    }

    @Override
    public void buildTypeAddedToQueue(@NotNull SQueuedBuild sQueuedBuild) {
        AddToLog("Build " + sQueuedBuild.getBuildType().getExtendedFullName() + " was added to queue");
    }

    @Override
    public void buildFinished(@NotNull SRunningBuild build) {
        AddToLog("Build " + build.getBuildType().getExtendedFullName() + " was finished");
    }

    private void AddToLog(String message){
        synchronized (myLog) {
            AddToLog(myLog, message);
        }
    }

    private void AddToLog(List<String> log, String message) {
        while (log.size() > 9) {
            log.remove(log.size() - 1);
        }
        log.add(0, DateFormat.getTimeInstance(DateFormat.SHORT).format(new Date()) + ": " + message);
    }

    public List<String> getMessages() {
        synchronized (myLog) {
            return new ArrayList<String>(myLog);
        }
    }
}
