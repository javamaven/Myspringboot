package seven.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

/**
 * Created by chenhaijun on 2017/1/6.
 */
@Controller
@RequestMapping(value = "/file")
public class FileController {

    @RequestMapping(value="")
    public ModelAndView go(){
        ModelAndView view = new ModelAndView("file/upload");
        return view;

    }

    @RequestMapping(value="upload",method= RequestMethod.POST)
    public String uploadFile(@RequestParam(value="file") MultipartFile file){

        try{
            if(file != null) {

                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File("d:\\1.jpg")));

                out.write(file.getBytes());

                out.flush();

                out.close();

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";

    }

    @RequestMapping(value="uploads",method= RequestMethod.POST)
    public String uploadFiles(HttpServletRequest request){

        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("file");

        System.out.println("--------------------="+files.size()+","+"当前类=FileController.uploadFile()");


        if(files != null && files.size()>0) {

                 System.out.println("--------------------="+files.size()+","+"当前类=FileController.uploadFile()");

            }

        return "success";

    }


}
