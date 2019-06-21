package com.example.demo.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.pojo.Category;
import com.example.demo.pojo.OrderItem;
import com.example.demo.pojo.User;
import com.example.demo.service.CategoryService;
import com.example.demo.service.OrderItemService;

public class OtherInterceptor implements HandlerInterceptor{
	
	@Autowired
	private OrderItemService orderitemserivce;
	@Autowired
	private CategoryService categoryservice;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		return true;  
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HttpSession session = request.getSession();
		User user =(User) session.getAttribute("user");
		int  cartTotalItemNumber = 0;
		if(null!=user) {
			List<OrderItem> listorderitem = orderitemserivce.listByUser(user);
			for(OrderItem item:listorderitem) {
				cartTotalItemNumber+=item.getNumber();
			}
	    }
		List<Category> listcategory = categoryservice.getCategoryList();
		String contextPath = request.getServletContext().getContextPath();
		request.getServletContext().setAttribute("categories_below_search", listcategory);
		session.setAttribute("cartTotalItemNumber", cartTotalItemNumber);
		request.getServletContext().setAttribute("contextPath", contextPath);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
