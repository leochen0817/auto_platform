package com.forte.auto.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.forte.auto.entity.User;
import com.forte.auto.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	UserService userService;

	@RequestMapping("/getAll")
	public @ResponseBody List<User> findAll() {
		return userService.getAll();
	}

	@RequestMapping("/getOne")
	public @ResponseBody User getOne(@RequestParam int id) {
		User user = this.userService.getOne(id);
		return user;
	}

	@RequestMapping("/insert")
	public @ResponseBody void insert(@RequestParam String email, @RequestParam String name) {
		User user = new User();
		user.setEmail(email);
		user.setName(name);
		this.userService.insert(user);
	}

	@RequestMapping("/update")
	public @ResponseBody void update(@RequestParam String email, @RequestParam String name, @RequestParam int id) {
		User user = this.userService.getOne(id);
		user.setEmail(email);
		user.setName(name);
		this.userService.update(user);
	}

	@RequestMapping("/delete")
	public @ResponseBody void delete(@RequestParam int id) {
		this.userService.delete(id);
	}

	@RequestMapping("/hello")
	public String welcome(Model model) {
		model.addAttribute("time", new Date());
		return "/pages/hello";
	}
	
}
