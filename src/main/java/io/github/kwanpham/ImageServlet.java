package io.github.kwanpham;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by https://github.com/kwanpham
 */
@WebServlet("/image/*")
public class ImageServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String uri = request.getRequestURI();

        //Lay ten anh
        String imageName = uri.substring("/image/".length());

        // Truyen du lieu cho response voi java >= 1.7
        Files.copy(Paths.get(UploadImageServlet.DOWNLOAD_PATH+ File.separator+imageName), response.getOutputStream());

    }

}

