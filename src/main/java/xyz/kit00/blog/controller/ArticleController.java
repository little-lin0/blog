package xyz.kit00.blog.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.java2d.pipe.SpanShapeRenderer;
import xyz.kit00.blog.bean.Article;
import xyz.kit00.blog.bean.User;
import xyz.kit00.blog.content.Sample;
import xyz.kit00.blog.service.ArticleService;
import xyz.kit00.blog.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/addArticle")
    public JSONObject addArticle(@RequestParam Map<String,String> map,
                                 @RequestPart("cover") MultipartFile cover, HttpServletRequest request)
            throws IOException {
        //        审核
        if(!Sample.checkText(map.values().toString())){
            JSONObject result=new JSONObject();
            result.put("msg","提交内容违规");
            result.put("code","500");
            return result;
        }
            else {
            String userId=request.getSession().getAttribute("userId").toString();
            String author_name=request.getSession().getAttribute("username").toString();;
            if(!cover.isEmpty()){
                //保存到文件服务器
                String originalFilename = cover.getOriginalFilename();
                File img = new File("F:\\project\\blog\\src\\main\\resources\\static\\img\\" + originalFilename);
                cover.transferTo(img);
            }
                    Article article=Article.builder()
                .authorId(Integer.parseInt(userId))
                .author_name(author_name)
                .title(map.get("title"))
                .sub_title(map.get("sub_title"))
                .content(map.get("content"))
                .label(map.get("label"))
                .cover("/img/"+cover.getOriginalFilename())
                .build();

            return articleService.addArticle(article);
        }






    }
    @RequestMapping("/articleList")
    public  JSONObject articleList(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,@RequestParam(value = "pageSize",defaultValue = "2") Integer pageSzie){
       JSONObject result=  articleService.searchAll(pageNum, pageSzie);
       return result;
    }
    @RequestMapping("/articleList/getTotal")
    public Integer getTotal(){
        return articleService.getTotal();
    }

    @RequestMapping("/searchArticle")
    public JSONObject searchArticleById(@RequestParam("id")Integer id){
        return articleService.selectByPrimaryKey(id);
    }

    @RequestMapping("/searchByCondition")
    public  JSONObject searchByCondition(@RequestParam Map<String,String> condition){
        return articleService.searchByCondition(condition);
    }
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public JSONObject updateArticle (@RequestParam Map<String,String> map,@RequestPart(value = "cover",required = false) MultipartFile cover) throws IOException{
            if(Sample.checkText(map.values().toString())){
                if(cover!=null&&!cover.isEmpty()){
                    //保存到文件服务器，OSS服务器
                    String originalFilename = cover.getOriginalFilename();
                    File img = new File("F:\\project\\blog\\src\\main\\resources\\static\\img\\" + originalFilename);
                    cover.transferTo(img);
                    map.put("cover","/img/"+cover.getOriginalFilename());
                }
                System.out.println("更新"+map);
                return articleService.update(map);
            }else {
                JSONObject result=new JSONObject();
                result.put("msg","提交内容违规");
                result.put("code","500");
                return result;
            }


    }

    @RequestMapping("/delete")
    public  JSONObject deleteArticle(@RequestParam("id") Integer id){
        return articleService.deleteArticle(id);
    }
    @RequestMapping("/collect")
    public  JSONObject collect(@RequestParam Map<String,String> map){
           return  articleService.collectService(map);
    }
    @RequestMapping("/follow")
    public  JSONObject follow(@RequestParam Map<String,String> map){
        return  articleService.followService(map);
    }
    @RequestMapping("/label")
    public JSONObject label(@RequestParam(value = "update",required = false) String update){
          return articleService.labelService(update);
    }
    @RequestMapping("/search_key")
    public JSONObject search_key(){
        return articleService.search_keyService();
    }
}
