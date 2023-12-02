package com.stock.project.user;

import com.stock.project.dto.SettingsPage;
import com.stock.project.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final TransactionService transactionService;

    @Autowired
    public UserService(UserRepository userRepository, TransactionService transactionService) {
        this.userRepository = userRepository;
        this.transactionService = transactionService;
    }

    public Boolean dailyLogin(User user) {
        LocalDate dateLastObtainedBonus = user.getLastBonus();
        LocalDate realDate = LocalDate.now();
        long daysBetween = ChronoUnit.DAYS.between(dateLastObtainedBonus, realDate);
        if (daysBetween >= 1) {
            if(transactionService.addBonus(user)) {
                user.setBalance(user.getBalance() + 500);
                userRepository.save(user);
                return true;
            }
            return false;
        } else {
            return false;
        }
    }

    public Boolean canDoHardWork(User user) {
        int val = user.getWorkDays();
        return val < 3;
    }

    public Boolean doHardWork(User user) {
        boolean b = canDoHardWork(user);
        if (b) {
            user.setBalance(user.getBalance() + 100);
            userRepository.save(user);
            transactionService.addWork(user);
        }
        return b;
    }

    public SettingsPage getUserInfo(User user){
        Long in = transactionService.netIn(user);
        Long out = transactionService.netOut(user);
        Long net = in - out;
        return new SettingsPage(in,out,net,user.getName(),user.getEmailVerified(),user.getPhoneNumberVerified());
    }
}
