package dev.despical.opensourcetab;

import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.VcsDataKeys;
import com.intellij.openapi.vcs.changes.Change;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Opens the working-tree version of the selected changed file in a regular editor tab.
 *
 * <p>The action is registered in the Commit tool window change context menu and in the
 * diff viewer toolbar. If the file is already open in an editor, its existing tab is focused
 * instead of opening a duplicate.
 *
 * @author Despical
 */
public class OpenSourceTabAction extends AnAction implements DumbAware {

    /**
     * Resolves the target file only from data keys, so the update can run off the EDT.
     *
     * @return {@link ActionUpdateThread#BGT}
     */
    @Override
    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return ActionUpdateThread.BGT;
    }

    /**
     * Finds the local file the action should open, from the selection or the current diff.
     *
     * @param e the action event providing the data context
     * @return a valid local non-directory file, or {@code null} when none is available
     */
    private static @Nullable VirtualFile resolve(@NotNull AnActionEvent e) {
        VirtualFile[] files = e.getData(CommonDataKeys.VIRTUAL_FILE_ARRAY);
        if (files != null) {
            for (VirtualFile file : files) {
                if (isOpenable(file)) return file;
            }
        }

        VirtualFile single = e.getData(CommonDataKeys.VIRTUAL_FILE);
        if (isOpenable(single)) return single;

        Change[] changes = e.getData(VcsDataKeys.CHANGES);
        if (changes != null) {
            for (Change change : changes) {
                VirtualFile file = change.getVirtualFile();
                if (isOpenable(file)) return file;
            }
        }

        return null;
    }

    private static boolean isOpenable(@Nullable VirtualFile file) {
        return file != null && file.isValid() && !file.isDirectory() && file.isInLocalFileSystem();
    }

    /** Shows the action only when a file to open can be resolved. */
    @Override
    public void update(@NotNull AnActionEvent e) {
        e.getPresentation().setEnabledAndVisible(e.getProject() != null && resolve(e) != null);
    }

    /** Opens the resolved file in an editor tab, focusing it if it is already open. */
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getProject();
        VirtualFile file = resolve(e);
        if (project == null || file == null) return;

        FileEditorManager.getInstance(project).openFile(file, true);
    }
}
