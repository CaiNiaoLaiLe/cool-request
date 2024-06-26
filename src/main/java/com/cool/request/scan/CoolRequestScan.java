package com.cool.request.scan;

import com.cool.request.common.listener.RefreshSuccessCallback;
import com.cool.request.components.ComponentType;
import com.cool.request.view.tool.UserProjectManager;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Computable;
import org.jetbrains.annotations.NotNull;

public class CoolRequestScan {

    /**
     * 静态方式，刷新视图
     *
     * @param project
     */
    public static void staticScan(@NotNull Project project, RefreshSuccessCallback refreshSuccessCallback) {
        ProgressManager.getInstance().run(new Task.Backgroundable(project, "Cool Request scan ...") {
            @Override
            public void run(@NotNull ProgressIndicator indicator) {
                ApplicationManager.getApplication().runReadAction((Computable<Object>) () -> {

                    UserProjectManager.getInstance(project).addComponent(ComponentType.CONTROLLER,
                            Scans.getInstance(project).scanController(project));
                    UserProjectManager.getInstance(project).addComponent(ComponentType.SCHEDULE,
                            Scans.getInstance(project).scanScheduled(project));
                    if (refreshSuccessCallback != null) refreshSuccessCallback.refreshFinish();
                    return null;
                });
            }
        });
    }
}
