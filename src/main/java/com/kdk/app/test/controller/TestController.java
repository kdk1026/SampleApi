package com.kdk.app.test.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.kdk.app.common.util.PagingUtil;
import com.kdk.app.common.util.captcha.CaptchaUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * <pre>
 * -----------------------------------
 * 개정이력
 * -----------------------------------
 * 2024. 7. 3. kdk	최초작성
 * </pre>
 *
 *
 * @author kdk
 */
@RestController
public class TestController {

	@GetMapping("/captcha-img/{rand}")
	public void captchaImg(HttpServletRequest request, HttpServletResponse response, @PathVariable String rand) throws Exception {
		new CaptchaUtil().getImgCaptCha(request, response, rand);
	}

	@GetMapping("/captcha-audio/{rand}")
	public void captchaAudio(HttpServletRequest request, HttpServletResponse response, @PathVariable String rand) throws Exception {
//		Captcha captcha = (Captcha) request.getSession().getAttribute(Captcha.NAME);
//		String getAnswer = captcha.getAnswer();
		String getAnswer = rand;
		new CaptchaUtil().getAudioCaptCha(request, response, getAnswer);
	}

	@GetMapping("/page")
	public Map<String, Object> page(String currentPage) {
		Map<String, Object> retMap = new HashMap<String, Object>();

		int pagePerRow = 5;
		int pagePerScreen = 5;
		int totalCnt = 100;

		PagingUtil pagingUtil = new PagingUtil(pagePerRow, pagePerScreen, totalCnt, currentPage, null);

		retMap.put("paging", pagingUtil);

		return retMap;
	}

}
