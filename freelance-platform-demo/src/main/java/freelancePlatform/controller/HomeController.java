package freelancePlatform.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import freelancePlatform.service.JobService;
import freelancePlatform.service.ProposalService;
import freelancePlatform.service.UserService;

@Controller
public class HomeController {
	@Autowired
	HttpSession session;
	
	@Autowired
	JobService jobService;
	
	@Autowired
	ProposalService proposalService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/")
	public String index(ModelMap model) {
		if(session.getAttribute("email") != null && session.getAttribute("role").equals(2)) {
			Long currentUserId = Long.valueOf(session.getAttribute("userid").toString());
			int postedJobsCount = jobService.findCountAllJobByUserAvailable(currentUserId);
			int hiringJobsCount = jobService.findCountAllJobByUserStatus(currentUserId, 1);
			int ongoingJobsCount = jobService.findCountAllJobByUserStatus(currentUserId, 2);
			int completedJobsCount = jobService.findCountAllJobByUserStatus(currentUserId, 3);
			model.addAttribute("postedJobsCount",postedJobsCount);
			model.addAttribute("hiringJobsCount",hiringJobsCount);
			model.addAttribute("ongoingJobsCount",ongoingJobsCount);
			model.addAttribute("completedJobsCount",completedJobsCount);
			
			int proposalCounts = proposalService.findCountAllProposalsByJobUser(currentUserId);
			int rejectedProposalCounts = proposalService.findCountAllProposalsByJobUserStatus(currentUserId, 0);
			int processingProposalCounts = proposalService.findCountAllProposalsByJobUserStatus(currentUserId, 1);
			int approvedProposalCounts = proposalService.findCountAllProposalsByJobUserStatus(currentUserId, 2);
			model.addAttribute("proposalCounts",proposalCounts);
			model.addAttribute("rejectedProposalCounts",rejectedProposalCounts);
			model.addAttribute("processingProposalCounts",processingProposalCounts);
			model.addAttribute("approvedProposalCounts",approvedProposalCounts);

			model.addAttribute("job1Count",jobService.findCountAllJobByUserAvailableByField(currentUserId, Long.valueOf("1")));
			model.addAttribute("job2Count",jobService.findCountAllJobByUserAvailableByField(currentUserId, Long.valueOf("2")));
			model.addAttribute("job3Count",jobService.findCountAllJobByUserAvailableByField(currentUserId, Long.valueOf("3")));
			model.addAttribute("job4Count",jobService.findCountAllJobByUserAvailableByField(currentUserId, Long.valueOf("4")));
			model.addAttribute("job5Count",jobService.findCountAllJobByUserAvailableByField(currentUserId, Long.valueOf("5")));
			model.addAttribute("job6Count",jobService.findCountAllJobByUserAvailableByField(currentUserId, Long.valueOf("6")));
			model.addAttribute("job7Count",jobService.findCountAllJobByUserAvailableByField(currentUserId, Long.valueOf("7")));
			model.addAttribute("job8Count",jobService.findCountAllJobByUserAvailableByField(currentUserId, Long.valueOf("8")));
			model.addAttribute("job9Count",jobService.findCountAllJobByUserAvailableByField(currentUserId, Long.valueOf("9")));
			model.addAttribute("job10Count",jobService.findCountAllJobByUserAvailableByField(currentUserId, Long.valueOf("10")));
			
			return "customer/index";
		}
		if(session.getAttribute("email") != null && session.getAttribute("role").equals(3)) {
			Long currentUserId = Long.valueOf(session.getAttribute("userid").toString());
			int ongoingJobsCount = jobService.findCountAllJobsByFreelancerAndStatus(currentUserId, 2);
			int completedJobsCount = jobService.findCountAllJobsByFreelancerAndStatus(currentUserId, 3);
			model.addAttribute("ongoingJobsCount",ongoingJobsCount);
			model.addAttribute("completedJobsCount",completedJobsCount);
			
			int proposalCounts = proposalService.findCountAllProposalsByFreelancer(currentUserId);
			int rejectedProposalCounts = proposalService.findCountAllProposalsByFreelancer(currentUserId, 0);
			int processingProposalCounts = proposalService.findCountAllProposalsByFreelancer(currentUserId, 1);
			int approvedProposalCounts = proposalService.findCountAllProposalsByFreelancer(currentUserId, 2);
			model.addAttribute("proposalCounts",proposalCounts);
			model.addAttribute("rejectedProposalCounts",rejectedProposalCounts);
			model.addAttribute("processingProposalCounts",processingProposalCounts);
			model.addAttribute("approvedProposalCounts",approvedProposalCounts);
			return "freelancer/index";
		}
		
		if(session.getAttribute("email") != null && session.getAttribute("role").equals(1)) {
			int jobsCount = jobService.findCountAllActiveJob();
			int hiringJobsCount = jobService.findCountAllActiveJobByStatus(1);
			int ongoingJobsCount = jobService.findCountAllActiveJobByStatus(2);
			int completedJobsCount = jobService.findCountAllActiveJobByStatus(3);
			model.addAttribute("jobsCount",jobsCount);
			model.addAttribute("hiringJobsCount",hiringJobsCount);
			model.addAttribute("ongoingJobsCount",ongoingJobsCount);
			model.addAttribute("completedJobsCount",completedJobsCount);
			
			int accountsCount = userService.findCountAllUser();
			int clientAccountsCount = userService.findCountAllUserByRole(2);
			int freelancerAccountsCount = userService.findCountAllUserByRole(3);
			model.addAttribute("accountsCount",accountsCount);
			model.addAttribute("clientAccountsCount",clientAccountsCount);
			model.addAttribute("freelancerAccountsCount",freelancerAccountsCount);
			
			model.addAttribute("job1Count",jobService.findCountAllActiveJobByField(Long.valueOf("1")));
			model.addAttribute("job2Count",jobService.findCountAllActiveJobByField(Long.valueOf("2")));
			model.addAttribute("job3Count",jobService.findCountAllActiveJobByField(Long.valueOf("3")));
			model.addAttribute("job4Count",jobService.findCountAllActiveJobByField(Long.valueOf("4")));
			model.addAttribute("job5Count",jobService.findCountAllActiveJobByField(Long.valueOf("5")));
			model.addAttribute("job6Count",jobService.findCountAllActiveJobByField(Long.valueOf("6")));
			model.addAttribute("job7Count",jobService.findCountAllActiveJobByField(Long.valueOf("7")));
			model.addAttribute("job8Count",jobService.findCountAllActiveJobByField(Long.valueOf("8")));
			model.addAttribute("job9Count",jobService.findCountAllActiveJobByField(Long.valueOf("9")));
			model.addAttribute("job10Count",jobService.findCountAllActiveJobByField(Long.valueOf("10")));
			
			return "admin/index";
		}
		
		return "site/index";
	}
}
