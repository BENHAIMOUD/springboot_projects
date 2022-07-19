package com.example.fileuploadcsv.controller;

import com.example.fileuploadcsv.entity.AccountDetails;
import com.example.fileuploadcsv.repository.AccountRepository;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BankController {


    @Autowired
    AccountRepository repository;

    @PostMapping("/upload")
    public String uploadData(@RequestParam("file")MultipartFile file) throws  Exception {



        List<AccountDetails> accountList = new ArrayList<>();
        InputStream inputStream = file.getInputStream();
        CsvParserSettings setting = new CsvParserSettings();
        setting.setHeaderExtractionEnabled(true);
        CsvParser parser = new CsvParser(setting);
        List<Record> parseAllRecords = parser.parseAllRecords(inputStream);
        parseAllRecords.forEach(record -> {
            AccountDetails account = new AccountDetails();

            account.setAccountNumber(Integer.parseInt(record.getString("account_number")));
            account.setFirstname(record.getString("firstname"));
            account.setLastname(record.getString("lastname"));
            account.setAccountType(record.getString("account_type"));
            account.setAddress(record.getString("address"));
            account.setEmail(record.getString("email"));
            account.setZipcode(record.getString("zipcode"));
            accountList.add(account);
        });

        repository.saveAll(accountList);
        return "Upload Successfully!";
    }
}
