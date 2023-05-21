package com.bump.bumpy.util.customannotation;

import com.bump.bumpy.util.dto.PasswordDto;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.HistoryRule;
import org.passay.LengthRule;
import org.passay.MatchBehavior;
import org.passay.PasswordData;
import org.passay.RuleResult;
import org.passay.UsernameRule;
import org.passay.WhitespaceRule;
import org.passay.spring.SpringMessageResolver;
import org.springframework.context.MessageSource;
import org.springframework.context.support.StaticMessageSource;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Locale;

import static com.bump.bumpy.util.funtion.FieldValueUtil.getUserId;

//https://www.baeldung.com/java-passay
public class PasswordValidator implements ConstraintValidator<Password, PasswordDto> {
	@Override
	public boolean isValid(PasswordDto validPassword, ConstraintValidatorContext context) {

		org.passay.PasswordValidator validator = new org.passay.PasswordValidator(
				new SpringMessageResolver(getKoreanMessageSource(), Locale.KOREA),
				List.of(
				new UsernameRule(MatchBehavior.Contains),
				new LengthRule(8, 20),
				new CharacterRule(EnglishCharacterData.LowerCase, 1),
				new CharacterRule(EnglishCharacterData.Digit, 1),
				new CharacterRule(EnglishCharacterData.Special, 1),
                new HistoryRule(),
				new WhitespaceRule()
				));
		PasswordData passwordData = new PasswordData(validPassword.getPassword());
		if(validPassword.getOldPassword() != null) {
			passwordData.setPasswordReferences(new PasswordData.HistoricalReference(validPassword.getOldPassword()));
		}
		if(getUserId() != "anonymousUser") {
			passwordData.setUsername(getUserId());
		}
		
		RuleResult result = validator.validate(passwordData);
		if(result.isValid()) {
			return true;
		}

		List<String> messages = validator.getMessages(result);
		String messageTemplate = messages.get(0);
		context.buildConstraintViolationWithTemplate(messageTemplate)
		.addConstraintViolation()
		.disableDefaultConstraintViolation();
		
		return false;
	}

	private MessageSource getKoreanMessageSource() {
		 final StaticMessageSource messageSource = new StaticMessageSource();
	        messageSource.addMessage("TOO_SHORT", 						Locale.KOREA, "비밀번호는 {0}-{1} 자리여야 합니다.");
	        messageSource.addMessage("TOO_LONG", 						Locale.KOREA, "비밀번호는 {0}-{1} 자리여야 합니다.");
	        messageSource.addMessage("INSUFFICIENT_LOWERCASE", 			Locale.KOREA, "비밀번호에 최소 {0}개 이상의 소문자가 있어야 합니다.");
	        messageSource.addMessage("INSUFFICIENT_DIGIT", 				Locale.KOREA, "비밀번호에 최소 {0}개 이상의 숫자가 있어야 합니다.");
	        messageSource.addMessage("INSUFFICIENT_SPECIAL", 			Locale.KOREA, "비밀번호에 최소 {0}개 이상의 특수문자가 있어야 합니다.");
	        messageSource.addMessage("ILLEGAL_WHITESPACE", 				Locale.KOREA, "비밀번호에 공백문자는 들어갈 수 없습니다.");
	        messageSource.addMessage("ILLEGAL_USERNAME", 				Locale.KOREA, "비밀번호에 사용자 ID가 들어갈 수 없습니다.");
	        messageSource.addMessage("HISTORY_VIOLATION", 				Locale.KOREA, "기존 비밀번호를 사용할 수 없습니다.");
	        return messageSource;
	}
}
