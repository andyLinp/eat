package com.better.eat.ctrl;

import com.better.eat.domain.ResponseBean;
import com.better.eat.domain.document.UserDocument;
import com.better.eat.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author andy
 */
@RestController
@RequestMapping(value = "/user")
public class UserCtrl {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserService userService;
    @Autowired
    public UserCtrl(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    ResponseEntity addUser(@RequestBody UserDocument bean){
        ResponseBean responseBean = new ResponseBean();
        try{
            return ResponseEntity.ok(userService.addUser(bean));
        }catch (RuntimeException e) {
            return ResponseEntity.ok(responseBean);
        } catch (Exception e){
            logger.error(e.toString());
            return  new ResponseEntity(responseBean, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    ResponseEntity updateUser(@RequestBody UserDocument bean){
        ResponseBean responseBean = new ResponseBean();
        try{
            return ResponseEntity.ok(userService.updateUser(bean));
        }catch (RuntimeException e) {
            return ResponseEntity.ok(responseBean);
        } catch (Exception e){
            logger.error(e.toString());
            return  new ResponseEntity(responseBean, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteUser(@PathVariable String id){
        ResponseBean responseBean = new ResponseBean();
        try{
            return ResponseEntity.ok(userService.deleteUser(id));
        }catch (RuntimeException e) {
            return ResponseEntity.ok(responseBean);
        } catch (Exception e){
            logger.error(e.toString());
            return  new ResponseEntity(responseBean, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/query")
    ResponseEntity getUserByConditions(@RequestBody HashMap<String,String> params){
        ResponseBean responseBean = new ResponseBean();
        try{
            return ResponseEntity.ok(userService.getUserByConditions(params));
        }catch (RuntimeException e) {
            return ResponseEntity.ok(responseBean);
        } catch (Exception e){
            logger.error(e.toString());
            return  new ResponseEntity(responseBean, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
