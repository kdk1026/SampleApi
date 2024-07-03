package com.kdk.app.common.util.captcha;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nl.captcha.Captcha;
import nl.captcha.audio.AudioCaptcha;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;
import nl.captcha.servlet.CaptchaServletUtil;
import nl.captcha.text.renderer.DefaultWordRenderer;

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
public class CaptchaUtil {

	private static int width = 150;	/*보안문자 이미지 가로크기*/
	private static int height = 50; /*보안문자 이미지 세로크기*/

	public void getImgCaptCha(HttpServletRequest req, HttpServletResponse res, String rand) {
		/*폰트 및 컬러 설정*/
		List<Font> fontList = new ArrayList<Font>();
		fontList.add(new Font("", Font.HANGING_BASELINE, 40));
		fontList.add(new Font("Courier", Font.ITALIC, 40));
		fontList.add(new Font("", Font.PLAIN, 40));
		List<Color> colorList = new ArrayList<Color>();
		colorList.add(Color.BLACK);

		Captcha captcha = new Captcha.Builder(width,  height)
				// .addText() 또는 아래와 같이 정의 : 6자리 숫자와 폰트 및 컬러 설정
				//new NumbersAnswerProducer(6)
				.addText(new SetTextProducer(rand), new DefaultWordRenderer(colorList, fontList))
				.addNoise().addBorder()
				.addBackground(new GradiatedBackgroundProducer())
				.build();

		/*JSP에서 Captcha 객체에 접근할 수 있도록 session에 저장*/
//		req.getSession().setAttribute(Captcha.NAME, captcha);
		CaptchaServletUtil.writeImage(res,  captcha.getImage());
	}

	/*CaptCha Audio 생성*/
	public void getAudioCaptCha(HttpServletRequest req, HttpServletResponse res, String answer) throws IOException {
//		HttpSession session = req.getSession();

//		Captcha captcha = (Captcha) session.getAttribute(Captcha.NAME);
		String getAnswer = answer;

//		if(getAnswer == null || getAnswer.equals("")) getAnswer = captcha.getAnswer();

		SetKorVoiceProducer vProd = new SetKorVoiceProducer();

		AudioCaptcha audiocaptcha = new AudioCaptcha.Builder()
				.addAnswer(new SetTextProducer(getAnswer))
//				.addNoise()	/*잡음 추가*/
				.addVoice(vProd)
				.build();

		CaptchaServletUtil.writeAudio(res,  audiocaptcha.getChallenge());
	}
}

