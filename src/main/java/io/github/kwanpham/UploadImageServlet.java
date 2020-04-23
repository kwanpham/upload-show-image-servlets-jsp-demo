package io.github.kwanpham;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by https://github.com/kwanpham
 */
@WebServlet("/UploadImageServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class UploadImageServlet extends HttpServlet {

    public static final String DOWNLOAD_PATH = "F:\\Downloads";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Upload file voi Servlet API >= 3.0
        for (Part part : request.getParts()) {

            // Lay ten file anh trong header
            String imageName = getFileName(part);

            // Kiem tra ten dinh dang file co hop le hay khong
            if (!validateImage(imageName)) {
                request.setAttribute("message", "This is not an image format !");
                request.getRequestDispatcher("/result.jsp").forward(request, response);
                return;
            }

            // Doi ten anh theo time upload , co the boi den dong nay neu khong can
            imageName = getFakeImageName(imageName);

            part.write(this.getFolderUpload().getAbsolutePath() + File.separator + imageName);
            request.setAttribute("imageName", imageName);
        }
        request.setAttribute("message", "Upload Image Success!");

        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }



    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename"))
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
        }
        return "NoName";
    }

    private boolean validateImage(String imageName) {
        {
            String[] s = imageName.split("\\.");
            if (s.length==0){
                return false;
            }
            String ext = s[s.length-1];
            switch (ext)
            {
                case "gif":
                    return true;
                case "png":
                    return true;
                case "jpg":
                    return true;
                case "jpeg":
                    return true;
                default:
                    return false;
            }
        }
    }

    private String getFakeImageName(String imageName) {
        String[] s = imageName.split("\\.");
        String ext = s[s.length-1];
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
        imageName = formatter.format(new Date()) + "." + ext;
        return imageName;
    }


    public File getFolderUpload() {
        File folderUpload = new File(DOWNLOAD_PATH);
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        return folderUpload;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }
}
