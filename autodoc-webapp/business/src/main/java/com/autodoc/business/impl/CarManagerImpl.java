package com.autodoc.business.impl;


import com.autodoc.business.contract.CarManager;
import com.autodoc.business.contract.EmployeeManager;
import com.autodoc.contract.CarService;
import com.autodoc.model.Car;
import com.autodoc.model.Employee;
import org.apache.log4j.Logger;
import org.springframework.http.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Named
public class CarManagerImpl extends GlobalManager implements CarManager {

    private static final String BASE_URL = "http://localhost:8087/autodoc/cars";
    private static final Logger LOGGER = Logger.getLogger(CarManagerImpl.class);

    private CarService carService;

    public CarManagerImpl(CarService carService) {
        this.carService = carService;
    }

    @Override
    public Car getByRegistration(String token, String registration) {
        LOGGER.info("trying to get car by registration");
        setupHeader(token);
        try {
            LOGGER.info("restTemplate ready");
            LOGGER.info("token: " + token);
            LOGGER.info("registration: " + registration);

            ResponseEntity<Car> response = restTemplate.exchange(BASE_URL+"/registration?registration="+registration, HttpMethod.GET, request, Car.class);
            return response.getBody();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new
                    BadCredentialsException("External system authentication failed");
        }
    }

    @Override
    public Car getById(String token, int id) {
        LOGGER.info("trying to get car by id");
        return (Car) carService.getById(token, id);
    }

   /* @Override
    public Employee getEmployee(String token, String login) {
        LOGGER.info("trying to get employee by login");
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(token);
        RestTemplate restTemplate = new RestTemplate();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", "Bearer " + token);
        LOGGER.info("headers are set");
        HttpEntity<?> entity = new HttpEntity<Object>("parameters", headers);
        try {
            LOGGER.info("restTemplate ready");
            LOGGER.info("token: " + token);
            LOGGER.info("login: " + login);
            ResponseEntity<Employee> res = restTemplate.exchange(BASE_URL + "/name?name=" + login, HttpMethod.GET, entity, Employee.class);
            System.out.println("deded: " + res.getBody());
            return res.getBody();
        } catch (Exception e) {
            System.out.println("error occured");
            throw new
                    BadCredentialsException("External system authentication failed");
        }

    }*/


   /* @Override
    public boolean resetPassword(String login, String password, String token) throws BusinessExceptionConnect {

        CheckTokenRequestType checkTokenRequestType = new CheckTokenRequestType();
        checkTokenRequestType.setToken(token);
        ResetPasswordRequestType resetPasswordRequestType = new ResetPasswordRequestType();
        resetPasswordRequestType.setLogin(login);
        resetPasswordRequestType.setPassword(password);
        logger.info("trying to reset");
        return getConnectServicePort().resetPassword(resetPasswordRequestType).isReturn();
    }

    @Override
    public boolean sendResetPasswordLink(String login, String email) throws BusinessExceptionConnect {
        RequestPasswordResetLinkRequestType requestPasswordResetLinkRequestType = new RequestPasswordResetLinkRequestType();
        requestPasswordResetLinkRequestType.setEmail(email);
        requestPasswordResetLinkRequestType.setLogin(login);

        return getConnectServicePort().requestPasswordResetLink(requestPasswordResetLinkRequestType).isReturn();
    }

    @Override
    public boolean switchReminder(String token, String login, boolean reminder) throws BusinessExceptionMember {
        SwitchReminderRequestType requestType = new SwitchReminderRequestType();
        requestType.setLogin(login);
        requestType.setToken(token);
        return getMemberServicePort().switchReminder(requestType).isReturn();

    }


    IConnectService getConnectServicePort() {
        if (connectService == null) connectService = new ConnectService();
        return connectService.getConnectServicePort();
    }

    IMemberService getMemberServicePort() {
        if (memberService == null) memberService = new MemberService();
        return memberService.getMemberServicePort();
    }

    Member convertMemberTypeOutIntoMember(String token, MemberTypeOut memberTypeOut) throws BusinessExceptionLoan, BusinessExceptionBook {
        Member member = new Member();
        member.setFirstName(memberTypeOut.getFirstName());
        member.setLastName(memberTypeOut.getLastName());
        member.setLogin(memberTypeOut.getLogin());
        member.setEmail(memberTypeOut.getEmail());
        // converting xml date into Date
        Date date = convertGregorianCalendarIntoDate(memberTypeOut.getDateJoin().toGregorianCalendar());
        member.setDateJoin(date);
        member.setRole(memberTypeOut.getRole());
        member.setReminder(memberTypeOut.isReminder());
        List<Loan> loans = convertLoanListTypeIntoList(token, memberTypeOut.getLoanListType());
        logger.info("got the loans converted: " + loans.size());
        member.setLoanList(loans);
        logger.info("all infos passed to the member");

        return member;
    }

    List<Loan> convertLoanListTypeIntoList(String token, LoanListType loanListType) throws BusinessExceptionLoan, BusinessExceptionBook {
        List<Loan> loanList = new ArrayList<>();
        logger.info("trying to convert LoanListType into List<Loan>");
        for (LoanTypeOut loanTypeOut : loanListType.getLoanTypeOut()
        ) {
            if (loanTypeOut.getEndDate() == null) {
                Loan loan = new Loan();
                loan.setId(loanTypeOut.getId());
                logger.info("ISBN get: " + loanTypeOut.getISBN());
                if (loanTypeOut.getStartDate() != null) {
                    logger.info("converting dates: " + loanTypeOut.getStartDate());
                    Date date;
                    date = convertGregorianCalendarIntoDate(loanTypeOut.getStartDate().toGregorianCalendar());
                    logger.info("converted startDate");
                    loan.setStartDate(date);
                    date = convertGregorianCalendarIntoDate(loanTypeOut.getPlannedEndDate().toGregorianCalendar());
                    logger.info("converted plannedEndDate");
                    loan.setPlannedEndDate(date);
                    loan.setRenewable(loanManager.isRenewable(token, loan.getId()));

                }
                logger.info("getting book: " + loanTypeOut.getBookTypeOut());
                if (loanTypeOut.getBookTypeOut() == null) {
                    logger.info("creating new Book");
                    logger.info("isbn: " + loanTypeOut.getISBN());
                    Book book = bookManager.getBookByISBN(token, loanTypeOut.getISBN());
                    loan.setBook(book);
                    logger.info("title: " + book.getTitle());
                } else {
                    loan.setBook(convertBookTypeOutIntoBook(loanTypeOut.getBookTypeOut()));
                }
                loan.setIsbn(loanTypeOut.getISBN());
                logger.info("setting isbn: " + loan.getIsbn());
                loan.setStatus(loanManager.getStatus(token, loan.getId()));
                logger.info("trying to convert Book");
                loanList.add(loan);
                logger.info("loan added to the list");
            }
        }

        logger.info("loan list size: " + loanList.size());
        return loanList;
    }

    Book convertBookTypeOutIntoBook(BookTypeOut bookTypeOut) {
        if (bookTypeOut == null) return null;
        logger.info("trying to convert book");
        Book book = new Book();
        book.setId(bookTypeOut.getId());
        book.setIsbn(bookTypeOut.getISBN());
        book.setTitle(bookTypeOut.getTitle());
        book.setAuthor(bookTypeOut.getAuthor());
        book.setEdition(bookTypeOut.getEdition());
        book.setPublicationYear(bookTypeOut.getPublicationYear());
        book.setNbPages(bookTypeOut.getNbPages());
        book.setKeywords(bookTypeOut.getKeywords());
        logger.info("book converted: " + book);
        return book;
    }

    Date convertGregorianCalendarIntoDate(GregorianCalendar gDate) {
        if (gDate != null) {
            XMLGregorianCalendar xmlCalendar;
            try {
                xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gDate);
                logger.info("converting date");
                return xmlCalendar.toGregorianCalendar().getTime();
            } catch (DatatypeConfigurationException e) {
                logger.error(e.getMessage());
            }
        }
        return null;

    }

    void setConnectService(ConnectService connectService) {
        this.connectService = connectService;
    }

    void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }
*/

}
