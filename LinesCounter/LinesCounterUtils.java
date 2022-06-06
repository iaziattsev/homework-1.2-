package LinesCounter;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LinesCounterUtils {

    public static final FileFilter DIRECTORY_FILTER = File::isDirectory;
    public static final FileFilter FILE_FILTER = File::isFile;

    public static int getTotalLinesOfCode(File file) {
        return getTotalLinesOfCode(file, "");
    }

    public static int getTotalLinesOfCode(File file, String extension) {
        var count = 0;

        var childDirs = new ArrayList<File>();
        getChildDirs(file, childDirs);

        if (childDirs.size() == 0)
            return count;

        for (var childDir : childDirs) {
            var childFiles = childDir.listFiles(FILE_FILTER);
            if (childFiles == null)
                continue;

            for (var childFile : childFiles) {
                if (!childFile.getAbsolutePath().contains("." + extension))
                    continue;
                try {
                    count += getFileLinesOfCode(childFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return count;
    }

    private static void getChildDirs(File file, List<File> dirs) {
        dirs.add(file);

        var index = 0;
        while (true) {
            if (index == dirs.size())
                break;

            var childDirs = dirs.get(index).listFiles(DIRECTORY_FILTER);
            if (childDirs == null)
                return;

            dirs.addAll(Arrays.stream(childDirs).toList());

            index++;
        }
    }

    private static long getFileLinesOfCode(File file) throws Exception {
        return Files.lines(file.toPath()).count();
    }

    /**
     * Be aware of stack overflow...
     * @deprecated
     */
    public static int getTotalLinesOfCodeRecursively(File file) {
        var count = 0;

        var childFiles = file.listFiles();
        if (childFiles == null)
            return count;

        for (var childFile : childFiles) {
            try {
                count += (childFile.isDirectory()) ?
                        getTotalLinesOfCodeRecursively(childFile) : getFileLinesOfCode(childFile);
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }

        return count;
    }
}
