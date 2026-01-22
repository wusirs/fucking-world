package com.world.fucking.test.compare;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


/**
 * list 排序 根据 birthday 时间排序，birthday 大于当前时间的数据放在小于当前时间的数据前面，然后再根据 birthday 按照时间顺序排序
 */
public class ListCompare {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        CompareUser userA = new CompareUser("5", "张三", simpleDateFormat.parse("2025-12-12 00:00:00"));
        CompareUser userB = new CompareUser("1", "李四", simpleDateFormat.parse("2025-01-12 00:00:00"));
        CompareUser userC = new CompareUser("4", "王二", simpleDateFormat.parse("2025-07-12 00:00:00"));
        CompareUser userD = new CompareUser("2", "赵钱", simpleDateFormat.parse("2025-05-12 00:00:00"));
        CompareUser userE = new CompareUser("3", "前六", null);
        List<CompareUser> users = new ArrayList<>(Arrays.asList(
                userA,
                userB,
                userC,
                userD,
                userE
        ));
        Date now = new Date();

        users.sort(Comparator.comparing((CompareUser k) -> {
                            Date birthday = k.getBirthday();
                            // 处理 birthday 为 null 的情况，此处将其视为过去日期
                            if (birthday == null) {
                                return 1; // 1 代表“过去”组
                            }
                            // 生日在未来则返回 0，在过去则返回 1。这样未来日期（0）会排在前面（升序时 0 小于 1）
                            return birthday.after(now) ? 0 : 1;
                        })
                        .thenComparing(CompareUser::getBirthday, Comparator.nullsLast(Comparator.naturalOrder()))
        );

        System.out.println(users);


        // 完全展开的比较器定义
        Comparator<CompareUser> birthdayComparator = (user1, user2) -> {
            Date bd1 = user1.getBirthday();
            Date bd2 = user2.getBirthday();

            // 手动实现 nullsLast 逻辑: 如果bd1为null而bd2不为null，则bd1"大于"bd2
            if (bd1 == null && bd2 != null) {
                return 1; // 正数表示bd1应排在bd2之后
            } else if (bd1 != null && bd2 == null) {
                return -1; // 负数表示bd1应排在bd2之前
            } else if (bd1 == null && bd2 == null) {
                return 0; // 相等，继续后续比较（如果有的话）
            } else {
                // 两个生日都不为null，使用自然顺序（升序）比较
                return bd1.compareTo(bd2); // 等价于 Comparator.naturalOrder().compare(bd1, bd2)
            }
        };


        users.sort(birthdayComparator);

        System.out.println(users);
    }
}


@Data
@AllArgsConstructor
@NoArgsConstructor
class CompareUser {
    private String userId;

    private String userName;

    private Date birthday;

    @Override
    public String toString() {

        if (birthday == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(birthday);
    }
}