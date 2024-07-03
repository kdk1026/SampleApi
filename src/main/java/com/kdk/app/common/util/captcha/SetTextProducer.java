package com.kdk.app.common.util.captcha;

import nl.captcha.text.producer.TextProducer;

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
public class SetTextProducer implements TextProducer {

	private String str;

	public SetTextProducer(String getAnswer) {
		this.str = getAnswer;
	}

	@Override
	public String getText() {
		return this.str;
	}

}
