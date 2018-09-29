package com;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.*;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;

public class PathDialog extends JDialog {
    private static JFileChooser jf;
    private static String saveload;

    private static JDialog pathDialog;

    public PathDialog(Frame frame, String saveload) {
        super(frame, saveload, true);
        this.saveload = saveload;
        pathDialog = this;
        pathDialog.setLocationRelativeTo(frame);
        pathDialog.setSize(200,125);
        init();
    }

    private void init() {
        jf = new JFileChooser();


        if (saveload.equals("Save")) {
            jf.setApproveButtonText("Save in Folder");
            jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        } else {
            jf.setApproveButtonText("Load");
            jf.setFileFilter(new jsonFilter());
        }

        getDir();


    }

    private void getDir() {
        int returnVal = jf.showOpenDialog(pathDialog);
        ObjectMapper mapper = new ObjectMapper();

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                switch (saveload) {
                    case "Save":
                        File file = jf.getSelectedFile();
                        String fullPath = file.getAbsolutePath();
                        mapper.writeValue(new File(fullPath + "/" + CurrentInfo.getCurrentBuild().getName() + ".json"), CurrentInfo.getCurrentBuild());
                        break;
                    case "Load":
                        File f = jf.getSelectedFile();
                        String fP = f.getAbsolutePath();
                        CurrentInfo.setCurrentBuild(mapper.readValue(new File(fP), Build.class));
                        MainWindow.updateAll();
                        break;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }


            pathDialog.dispose();
        }
    }

    public class jsonFilter extends FileFilter {

        //Accept all directories and all gif, jpg, tiff, or png files.
        public boolean accept(File f) {
            if (f.isDirectory()) {
                return true;
            }

            Utils u = new Utils();

            String extension = u.getExtension(f);
            if (extension != null) {
                if (extension.equals(Utils.json)) {
                    return true;
                } else {
                    return false;
                }
            }

            return false;
        }

        //The description of this filter
        public String getDescription() {
            return "Just JSON Files please";
        }
    }

    private class Utils {

        public final static String jpeg = "jpeg";
        public final static String jpg = "jpg";
        public final static String gif = "gif";
        public final static String tiff = "tiff";
        public final static String tif = "tif";
        public final static String png = "png";
        public final static String json = "json";

        /*
         * Get the extension of a file.
         */
        public String getExtension(File f) {
            String ext = null;
            String s = f.getName();
            int i = s.lastIndexOf('.');

            if (i > 0 &&  i < s.length() - 1) {
                ext = s.substring(i+1).toLowerCase();
            }
            return ext;
        }
    }


}
