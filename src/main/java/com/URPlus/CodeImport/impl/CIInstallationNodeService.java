package com.URPlus.CodeImport.impl;

import java.util.Locale;

import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.contribution.installation.ContributionConfiguration;
import com.ur.urcap.api.contribution.installation.CreationContext;
import com.ur.urcap.api.contribution.installation.InstallationAPIProvider;
import com.ur.urcap.api.contribution.installation.swing.SwingInstallationNodeService;
import com.ur.urcap.api.domain.data.DataModel;

public class CIInstallationNodeService
		implements SwingInstallationNodeService<CIInstallationNodeContribution, CIInstallationNodeView> {

	public CIInstallationNodeService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void configureContribution(ContributionConfiguration configuration) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getTitle(Locale locale) {
		// TODO Auto-generated method stub
		return "Code Import";
	}

	@Override
	public CIInstallationNodeView createView(ViewAPIProvider apiProvider) {
		// TODO Auto-generated method stub
		return new CIInstallationNodeView(apiProvider);
	}

	@Override
	public CIInstallationNodeContribution createInstallationNode(InstallationAPIProvider apiProvider,
			CIInstallationNodeView view, DataModel model, CreationContext context) {
		// TODO Auto-generated method stub
		return new CIInstallationNodeContribution(apiProvider, view, model, context);
	}

}