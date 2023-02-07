package com.zy.testdatagenerator.controller;

import net.andreinc.mockneat.unit.text.sql.SQLInsert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static java.time.format.DateTimeFormatter.BASIC_ISO_DATE;
import static net.andreinc.mockneat.types.enums.MarkovChainType.LOREM_IPSUM;
import static net.andreinc.mockneat.unit.seq.IntSeq.intSeq;
import static net.andreinc.mockneat.unit.text.Markovs.markovs;
import static net.andreinc.mockneat.unit.text.SQLInserts.sqlInserts;
import static net.andreinc.mockneat.unit.text.sql.escapers.PostgreSQL.TEXT_BACKSLASH;
import static net.andreinc.mockneat.unit.time.LocalDates.localDates;
import static net.andreinc.mockneat.unit.user.Emails.emails;
import static net.andreinc.mockneat.unit.user.Names.names;
import static net.andreinc.mockneat.unit.user.Users.users;

@RequestMapping("/test")
@RestController
public class TestController {

    @GetMapping("/test1")
    public Object test1() throws IOException {
        SQLInsert oneInsert = sqlInserts()
                .tableName("emp")
                .column("id", intSeq().increment(10))
                .column("first_name", names().first(), TEXT_BACKSLASH)
                .column("last_name", names().last(), TEXT_BACKSLASH)
                .column("username", users(), TEXT_BACKSLASH)
                .column("email", emails(), TEXT_BACKSLASH)
                .column("description", markovs().size(32).type(LOREM_IPSUM), TEXT_BACKSLASH)
                .column("created", localDates().thisYear().display(BASIC_ISO_DATE), TEXT_BACKSLASH)
                .get();
// toString() was overriden to return the actual SQL Insert
        System.out.println(oneInsert);
// Possible Output:
// INSERT INTO emp (id, first_name, last_name, username, email, description, created) VALUES (0, 'Mohammad', 'Hibbets', 'lushtraci', 'villousslags@gmx.com', 'Ante ipsum primis in faucibus. E', '20180724');
        return "";
    }

}
