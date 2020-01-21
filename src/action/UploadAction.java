package action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import pojo.Friend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.UUID;

public class UploadAction extends ActionSupport implements ModelDriven<Friend> {
    private Friend friend;

    private File photo; //上传的文件，属性名需要与文件域name属性值保持一致
    private String photoFileName;
    private String photoContentType;

    @Override
    public Friend getModel() {
        if(friend==null){
            friend=new Friend();
        }
        return friend;
    }

    public File getPhoto() {
        return photo;
    }

    public void setPhoto(File photo) {
        this.photo = photo;
    }

    public String getPhotoFileName() {
        return photoFileName;
    }

    public void setPhotoFileName(String photoFileName) {
        this.photoFileName = photoFileName;
    }

    public String getPhotoContentType() {
        return photoContentType;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }

    /**
     * 上传
     */
    public String publish() throws IOException {
        System.out.println(this.photoFileName);
        System.out.println(this.photoContentType);

        //上传路径
        String realPath =ServletActionContext.getServletContext().getRealPath("/upload");
        String suffix=this.photoFileName.substring(this.photoFileName.lastIndexOf("."));
        //重命名，使用UUID保证唯一性，防止文件覆盖
        String newFileName=UUID.randomUUID().toString().replace("-","");
        System.out.println("新文件名："+newFileName);
        File uploadFile =new File(realPath+File.separator+ newFileName+suffix);
        System.out.println("上传路径+文件名："+uploadFile);

        InputStream in=new FileInputStream(this.photo);
        FileUtils.copyInputStreamToFile(in,uploadFile);

        //FileUtils.copyFile(this.photo,uploadFile); //此方式也可以实现

        System.out.println("上传成功");
        //out/artifacts/struts2_upload_download_war_exploded/upload/3a2d044c05d642db9a2c2463da5afae5.jpg

        this.friend.setPhotoPath(newFileName+suffix); //保存至数据库的文件名；保存文件名更加灵活


        return SUCCESS;
    }


    /**
     * 下载（通用）
     */
    public void download(){

        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            HttpServletResponse response = ServletActionContext.getResponse();

            // 获取要下载的文件名
            String fileName = request.getParameter("fileName");
            fileName=new String(fileName.getBytes("ISO-8859-1"),"UTF-8");
            // 获取要下载文件的路径
            String realPath = request.getServletContext().getRealPath("/upload");
            // 设置响应头，控制浏览器下载该文件
            response.setHeader("content-disposition", "attachment;filename="
                    + URLEncoder.encode(fileName, "UTF-8"));

            // 读取要下载的文件，保存至文件输入流
            //可以给IO加Buff
            InputStream in = new FileInputStream(realPath + File.separator
                    + fileName);
            // 创建输出流
            OutputStream output = response.getOutputStream();
            // 设置缓存区
            byte[] bytes = new byte[in.available()];
            int len = 0;
            while ((len = in.read(bytes)) > 0) {
                output.write(bytes);
            }
            // 关闭输入流
            in.close();
            // 关闭输出流
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
