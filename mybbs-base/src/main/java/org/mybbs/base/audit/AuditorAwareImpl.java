package org.mybbs.base.audit;

import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<String> {

	public String getCurrentAuditor() {
		return "SYSTEM";
	}

}
