package com.tangcheng.learning.date;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Locale;

import static org.hamcrest.CoreMatchers.is;

/**
 * spring-boot-cookbook
 *
 * @author : tang.cheng
 * @version : 2017-07-19  16:05
 */
@Slf4j
public class LocalDateApiTest {

    public static final Logger LOGGER = LoggerFactory.getLogger(LocalDateApiTest.class);

    @Test
    public void testLocalDateTime() {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        LocalDateTime parse = LocalDateTime.parse("2017-08-19 15:59:11", DateTimeFormatter.ofPattern(pattern));
        String format = parse.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LOGGER.info(format);
    }

    @Test(expected = DateTimeParseException.class)
    public void testLocalDateTimeFomatter() {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        LocalDateTime.parse("2017-8-19 15:59:11", DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void test() {
        //LocalDate,LocalTime,LocalDateTime的时间格式，严格按照ISO
        LOGGER.info(LocalDate.now().toString());//2017-07-19
        LOGGER.info(LocalTime.now().toString());//17:12:58.444
        LOGGER.info(LocalTime.now().withNano(0).toString());//17:12:58
        LOGGER.info(LocalDateTime.now().toString());//2017-07-19T17:12:58.444
        // 根据年月日取日期，12月就是12：
        Assert.assertThat(LocalDate.of(2014, 12, 25), is(LocalDate.parse("2014-12-25")));

        LOGGER.info("本月第一天（日期格式(ISO)：yyyy-MM-dd）:{}", LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()));
        LOGGER.info("本月第二天（日期格式(ISO)：yyyy-MM-dd）:{}", LocalDate.now().withDayOfMonth(2));
        LOGGER.info("本月最后一天（日期格式(ISO)：yyyy-MM-dd）:{}", LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()));
        LOGGER.info("上月最后一天（日期格式(ISO)：yyyy-MM-dd）:{}", LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()).minusDays(1));
        LOGGER.info("本月第二天（日期格式(ISO)：yyyy-MM-dd）:{}", LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()).plusDays(1));
        LOGGER.info("本月第一个周一（日期格式(ISO)：yyyy-MM-dd）:{}", LocalDate.now().with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)));
    }

    @Test
    public void testInstant() {
        //Instant类代表的是某个时间（有点象java.util.Date），它是精确到纳秒的（而不是象旧版本的Date精确到毫秒）
        // 其内部是由两个Long字段组成，
        // 第一个部分保存的是自标准Java计算时代（就是1970年1月1日开始）到现在的秒数，
        // 第二部分保存的是纳秒数（永远不会超过999,999,999）
        //以ISO-8601输出
        // 国际标准化组织的国际标准ISO 8601是日期和时间的表示方法，全称为《数据存储和交换形式·信息交换·日期和时间的表示方法》
        // 年为4位数，月为2位数，月中的日为2位数，例如2004年5月3日可写成2004-05-03或20040503。
        // 小时、分和秒都用2位数表示，对UTC时间最后加一个大写字母Z，其他时区用实际时间加时差表示。
        // 如UTC时间下午2点30分5秒表示为14:30:05Z或143005Z，
        // 当时的北京时间表示为22:30:05+08:00或223005+0800，也可以简化成223005+08。
        // 合并表示时，要在时间前面加一大写字母T，
        // 如要表示北京时间2004年5月3日下午5点30分8秒，可以写成2004-05-03T17:30:08+08:00或20040503T173008+08。
        Instant instant = Instant.now();
        System.out.println(instant);//2017-07-23T05:35:04.431Z
        //将java.util.Date转换为Instant
        Instant fromDate = Instant.ofEpochMilli(new Date().getTime());
        System.out.println(fromDate);//2017-07-25T05:42:33.897Z
        //从字符串类型中创建Instant类型的时间
        instant = Instant.parse("2017-07-23T10:12:35Z");
        System.out.println(instant);//2017-07-23T10:12:35Z


    }

    // 01. java.util.Date --> java.time.LocalDateTime
    @Test
    public void UDateToLocalDateTime() {
        Date date = new Date();
        System.out.println("date:" + date);
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        System.out.println("localDateTime:" + localDateTime);
    }

    // 02. java.util.Date --> java.time.LocalDate
    @Test
    public void UDateToLocalDate() {
        Date date = new Date();
        System.out.println("date:" + date);
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDate localDate = localDateTime.toLocalDate();
        System.out.println("localDate:" + localDate);
    }


    // 03. java.util.Date --> java.time.LocalTime
    @Test
    public void UDateToLocalTime() {
        Date date = new Date();
        System.out.println("date:" + date);

        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalTime localTime = localDateTime.toLocalTime();
        System.out.println("localTime:" + localTime);
    }


    // 04. java.time.LocalDateTime --> java.util.Date
    @Test
    public void LocalDateTimeToUdate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime:" + localDateTime);

        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        Date date = Date.from(instant);
        System.out.println("date:" + date);

    }


    // 05. java.time.LocalDate --> java.util.Date
    @Test
    public void LocalDateToUdate() {
        LocalDate localDate = LocalDate.now();
        System.out.println("localDate:" + localDate);

        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        Date date = Date.from(instant);
        System.out.println("date:" + date);

    }

    // 06. java.time.LocalTime --> java.util.Date
    @Test
    public void LocalTimeToUdate() {
        LocalTime localTime = LocalTime.now();
        System.out.println("localTime:" + localTime);

        LocalDate localDate = LocalDate.now();
        System.out.println("localDate:" + localDate);

        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        Date date = Date.from(instant);
        System.out.println("date:" + date);
    }

    @Test
    public void getAvailableZoneIds() {
        /**
         * Asia/Aden
         America/Cuiaba
         Etc/GMT+9
         Etc/GMT+8
         Africa/Nairobi
         America/Marigot
         Asia/Aqtau
         Pacific/Kwajalein
         America/El_Salvador
         Asia/Pontianak
         Africa/Cairo
         Pacific/Pago_Pago
         Africa/Mbabane
         Asia/Kuching
         Pacific/Honolulu
         Pacific/Rarotonga
         America/Guatemala
         Australia/Hobart
         Europe/London
         America/Belize
         America/Panama
         Asia/Chungking
         America/Managua
         America/Indiana/Petersburg
         Asia/Yerevan
         Europe/Brussels
         GMT
         Europe/Warsaw
         America/Chicago
         Asia/Kashgar
         Chile/Continental
         Pacific/Yap
         CET
         Etc/GMT-1
         Etc/GMT-0
         Europe/Jersey
         America/Tegucigalpa
         Etc/GMT-5
         Europe/Istanbul
         America/Eirunepe
         Etc/GMT-4
         America/Miquelon
         Etc/GMT-3
         Europe/Luxembourg
         Etc/GMT-2
         Etc/GMT-9
         America/Argentina/Catamarca
         Etc/GMT-8
         Etc/GMT-7
         Etc/GMT-6
         Europe/Zaporozhye
         Canada/Yukon
         Canada/Atlantic
         Atlantic/St_Helena
         Australia/Tasmania
         Libya
         Europe/Guernsey
         America/Grand_Turk
         US/Pacific-New
         Asia/Samarkand
         America/Argentina/Cordoba
         Asia/Phnom_Penh
         Africa/Kigali
         Asia/Almaty
         US/Alaska
         Asia/Dubai
         Europe/Isle_of_Man
         America/Araguaina
         Cuba
         Asia/Novosibirsk
         America/Argentina/Salta
         Etc/GMT+3
         Africa/Tunis
         Etc/GMT+2
         Etc/GMT+1
         Pacific/Fakaofo
         Africa/Tripoli
         Etc/GMT+0
         Israel
         Africa/Banjul
         Etc/GMT+7
         Indian/Comoro
         Etc/GMT+6
         Etc/GMT+5
         Etc/GMT+4
         Pacific/Port_Moresby
         US/Arizona
         Antarctica/Syowa
         Indian/Reunion
         Pacific/Palau
         Europe/Kaliningrad
         America/Montevideo
         Africa/Windhoek
         Asia/Karachi
         Africa/Mogadishu
         Australia/Perth
         Brazil/East
         Etc/GMT
         Asia/Chita
         Pacific/Easter
         Antarctica/Davis
         Antarctica/McMurdo
         Asia/Macao
         America/Manaus
         Africa/Freetown
         Europe/Bucharest
         Asia/Tomsk
         America/Argentina/Mendoza
         Asia/Macau
         Europe/Malta
         Mexico/BajaSur
         Pacific/Tahiti
         Africa/Asmera
         Europe/Busingen
         America/Argentina/Rio_Gallegos
         Africa/Malabo
         Europe/Skopje
         America/Catamarca
         America/Godthab
         Europe/Sarajevo
         Australia/ACT
         GB-Eire
         Africa/Lagos
         America/Cordoba
         Europe/Rome
         Asia/Dacca
         Indian/Mauritius
         Pacific/Samoa
         America/Regina
         America/Fort_Wayne
         America/Dawson_Creek
         Africa/Algiers
         Europe/Mariehamn
         America/St_Johns
         America/St_Thomas
         Europe/Zurich
         America/Anguilla
         Asia/Dili
         America/Denver
         Africa/Bamako
         Europe/Saratov
         GB
         Mexico/General
         Pacific/Wallis
         Europe/Gibraltar
         Africa/Conakry
         Africa/Lubumbashi
         Asia/Istanbul
         America/Havana
         NZ-CHAT
         Asia/Choibalsan
         America/Porto_Acre
         Asia/Omsk
         Europe/Vaduz
         US/Michigan
         Asia/Dhaka
         America/Barbados
         Europe/Tiraspol
         Atlantic/Cape_Verde
         Asia/Yekaterinburg
         America/Louisville
         Pacific/Johnston
         Pacific/Chatham
         Europe/Ljubljana
         America/Sao_Paulo
         Asia/Jayapura
         America/Curacao
         Asia/Dushanbe
         America/Guyana
         America/Guayaquil
         America/Martinique
         Portugal
         Europe/Berlin
         Europe/Moscow
         Europe/Chisinau
         America/Puerto_Rico
         America/Rankin_Inlet
         Pacific/Ponape
         Europe/Stockholm
         Europe/Budapest
         America/Argentina/Jujuy
         Australia/Eucla
         Asia/Shanghai
         Universal
         Europe/Zagreb
         America/Port_of_Spain
         Europe/Helsinki
         Asia/Beirut
         Asia/Tel_Aviv
         Pacific/Bougainville
         US/Central
         Africa/Sao_Tome
         Indian/Chagos
         America/Cayenne
         Asia/Yakutsk
         Pacific/Galapagos
         Australia/North
         Europe/Paris
         Africa/Ndjamena
         Pacific/Fiji
         America/Rainy_River
         Indian/Maldives
         Australia/Yancowinna
         SystemV/AST4
         Asia/Oral
         America/Yellowknife
         Pacific/Enderbury
         America/Juneau
         Australia/Victoria
         America/Indiana/Vevay
         Asia/Tashkent
         Asia/Jakarta
         Africa/Ceuta
         Asia/Barnaul
         America/Recife
         America/Buenos_Aires
         America/Noronha
         America/Swift_Current
         Australia/Adelaide
         America/Metlakatla
         Africa/Djibouti
         America/Paramaribo
         Europe/Simferopol
         Europe/Sofia
         Africa/Nouakchott
         Europe/Prague
         America/Indiana/Vincennes
         Antarctica/Mawson
         America/Kralendijk
         Antarctica/Troll
         Europe/Samara
         Indian/Christmas
         America/Antigua
         Pacific/Gambier
         America/Indianapolis
         America/Inuvik
         America/Iqaluit
         Pacific/Funafuti
         UTC
         Antarctica/Macquarie
         Canada/Pacific
         America/Moncton
         Africa/Gaborone
         Pacific/Chuuk
         Asia/Pyongyang
         America/St_Vincent
         Asia/Gaza
         Etc/Universal
         PST8PDT
         Atlantic/Faeroe
         Asia/Qyzylorda
         Canada/Newfoundland
         America/Kentucky/Louisville
         America/Yakutat
         Asia/Ho_Chi_Minh
         Antarctica/Casey
         Europe/Copenhagen
         Africa/Asmara
         Atlantic/Azores
         Europe/Vienna
         ROK
         Pacific/Pitcairn
         America/Mazatlan
         Australia/Queensland
         Pacific/Nauru
         Europe/Tirane
         Asia/Kolkata
         SystemV/MST7
         Australia/Canberra
         MET
         Australia/Broken_Hill
         Europe/Riga
         America/Dominica
         Africa/Abidjan
         America/Mendoza
         America/Santarem
         Kwajalein
         America/Asuncion
         Asia/Ulan_Bator
         NZ
         America/Boise
         Australia/Currie
         EST5EDT
         Pacific/Guam
         Pacific/Wake
         Atlantic/Bermuda
         America/Costa_Rica
         America/Dawson
         Asia/Chongqing
         Eire
         Europe/Amsterdam
         America/Indiana/Knox
         America/North_Dakota/Beulah
         Africa/Accra
         Atlantic/Faroe
         Mexico/BajaNorte
         America/Maceio
         Etc/UCT
         Pacific/Apia
         GMT0
         America/Atka
         Pacific/Niue
         Canada/East-Saskatchewan
         Australia/Lord_Howe
         Europe/Dublin
         Pacific/Truk
         MST7MDT
         America/Monterrey
         America/Nassau
         America/Jamaica
         Asia/Bishkek
         America/Atikokan
         Atlantic/Stanley
         Australia/NSW
         US/Hawaii
         SystemV/CST6
         Indian/Mahe
         Asia/Aqtobe
         America/Sitka
         Asia/Vladivostok
         Africa/Libreville
         Africa/Maputo
         Zulu
         America/Kentucky/Monticello
         Africa/El_Aaiun
         Africa/Ouagadougou
         America/Coral_Harbour
         Pacific/Marquesas
         Brazil/West
         America/Aruba
         America/North_Dakota/Center
         America/Cayman
         Asia/Ulaanbaatar
         Asia/Baghdad
         Europe/San_Marino
         America/Indiana/Tell_City
         America/Tijuana
         Pacific/Saipan
         SystemV/YST9
         Africa/Douala
         America/Chihuahua
         America/Ojinaga
         Asia/Hovd
         America/Anchorage
         Chile/EasterIsland
         America/Halifax
         Antarctica/Rothera
         America/Indiana/Indianapolis
         US/Mountain
         Asia/Damascus
         America/Argentina/San_Luis
         America/Santiago
         Asia/Baku
         America/Argentina/Ushuaia
         Atlantic/Reykjavik
         Africa/Brazzaville
         Africa/Porto-Novo
         America/La_Paz
         Antarctica/DumontDUrville
         Asia/Taipei
         Antarctica/South_Pole
         Asia/Manila
         Asia/Bangkok
         Africa/Dar_es_Salaam
         Poland
         Atlantic/Madeira
         Antarctica/Palmer
         America/Thunder_Bay
         Africa/Addis_Ababa
         Asia/Yangon
         Europe/Uzhgorod
         Brazil/DeNoronha
         Asia/Ashkhabad
         Etc/Zulu
         America/Indiana/Marengo
         America/Creston
         America/Punta_Arenas
         America/Mexico_City
         Antarctica/Vostok
         Asia/Jerusalem
         Europe/Andorra
         US/Samoa
         PRC
         Asia/Vientiane
         Pacific/Kiritimati
         America/Matamoros
         America/Blanc-Sablon
         Asia/Riyadh
         Iceland
         Pacific/Pohnpei
         Asia/Ujung_Pandang
         Atlantic/South_Georgia
         Europe/Lisbon
         Asia/Harbin
         Europe/Oslo
         Asia/Novokuznetsk
         CST6CDT
         Atlantic/Canary
         America/Knox_IN
         Asia/Kuwait
         SystemV/HST10
         Pacific/Efate
         Africa/Lome
         America/Bogota
         America/Menominee
         America/Adak
         Pacific/Norfolk
         Europe/Kirov
         America/Resolute
         Pacific/Tarawa
         Africa/Kampala
         Asia/Krasnoyarsk
         Greenwich
         SystemV/EST5
         America/Edmonton
         Europe/Podgorica
         Australia/South
         Canada/Central
         Africa/Bujumbura
         America/Santo_Domingo
         US/Eastern
         Europe/Minsk
         Pacific/Auckland
         Africa/Casablanca
         America/Glace_Bay
         Canada/Eastern
         Asia/Qatar
         Europe/Kiev
         Singapore
         Asia/Magadan
         SystemV/PST8
         America/Port-au-Prince
         Europe/Belfast
         America/St_Barthelemy
         Asia/Ashgabat
         Africa/Luanda
         America/Nipigon
         Atlantic/Jan_Mayen
         Brazil/Acre
         Asia/Muscat
         Asia/Bahrain
         Europe/Vilnius
         America/Fortaleza
         Etc/GMT0
         US/East-Indiana
         America/Hermosillo
         America/Cancun
         Africa/Maseru
         Pacific/Kosrae
         Africa/Kinshasa
         Asia/Kathmandu
         Asia/Seoul
         Australia/Sydney
         America/Lima
         Australia/LHI
         America/St_Lucia
         Europe/Madrid
         America/Bahia_Banderas
         America/Montserrat
         Asia/Brunei
         America/Santa_Isabel
         Canada/Mountain
         America/Cambridge_Bay
         Asia/Colombo
         Australia/West
         Indian/Antananarivo
         Australia/Brisbane
         Indian/Mayotte
         US/Indiana-Starke
         Asia/Urumqi
         US/Aleutian
         Europe/Volgograd
         America/Lower_Princes
         America/Vancouver
         Africa/Blantyre
         America/Rio_Branco
         America/Danmarkshavn
         America/Detroit
         America/Thule
         Africa/Lusaka
         Asia/Hong_Kong
         Iran
         America/Argentina/La_Rioja
         Africa/Dakar
         SystemV/CST6CDT
         America/Tortola
         America/Porto_Velho
         Asia/Sakhalin
         Etc/GMT+10
         America/Scoresbysund
         Asia/Kamchatka
         Asia/Thimbu
         Africa/Harare
         Etc/GMT+12
         Etc/GMT+11
         Navajo
         America/Nome
         Europe/Tallinn
         Turkey
         Africa/Khartoum
         Africa/Johannesburg
         Africa/Bangui
         Europe/Belgrade
         Jamaica
         Africa/Bissau
         Asia/Tehran
         WET
         Europe/Astrakhan
         Africa/Juba
         America/Campo_Grande
         America/Belem
         Etc/Greenwich
         Asia/Saigon
         America/Ensenada
         Pacific/Midway
         America/Jujuy
         Africa/Timbuktu
         America/Bahia
         America/Goose_Bay
         America/Virgin
         America/Pangnirtung
         Asia/Katmandu
         America/Phoenix
         Africa/Niamey
         America/Whitehorse
         Pacific/Noumea
         Asia/Tbilisi
         America/Montreal
         Asia/Makassar
         America/Argentina/San_Juan
         Hongkong
         UCT
         Asia/Nicosia
         America/Indiana/Winamac
         SystemV/MST7MDT
         America/Argentina/ComodRivadavia
         America/Boa_Vista
         America/Grenada
         Asia/Atyrau
         Australia/Darwin
         Asia/Khandyga
         Asia/Kuala_Lumpur
         Asia/Famagusta
         Asia/Thimphu
         Asia/Rangoon
         Europe/Bratislava
         Asia/Calcutta
         America/Argentina/Tucuman
         Asia/Kabul
         Indian/Cocos
         Japan
         Pacific/Tongatapu
         America/New_York
         Etc/GMT-12
         Etc/GMT-11
         Etc/GMT-10
         SystemV/YST9YDT
         Europe/Ulyanovsk
         Etc/GMT-14
         Etc/GMT-13
         W-SU
         America/Merida
         EET
         America/Rosario
         Canada/Saskatchewan
         America/St_Kitts
         Arctic/Longyearbyen
         America/Fort_Nelson
         America/Caracas
         America/Guadeloupe
         Asia/Hebron
         Indian/Kerguelen
         SystemV/PST8PDT
         Africa/Monrovia
         Asia/Ust-Nera
         Egypt
         Asia/Srednekolymsk
         America/North_Dakota/New_Salem
         Asia/Anadyr
         Australia/Melbourne
         Asia/Irkutsk
         America/Shiprock
         America/Winnipeg
         Europe/Vatican
         Asia/Amman
         Etc/UTC
         SystemV/AST4ADT
         Asia/Tokyo
         America/Toronto
         Asia/Singapore
         Australia/Lindeman
         America/Los_Angeles
         SystemV/EST5EDT
         Pacific/Majuro
         America/Argentina/Buenos_Aires
         Europe/Nicosia
         Pacific/Guadalcanal
         Europe/Athens
         US/Pacific
         Europe/Monaco
         */
        for (String zone : ZoneId.getAvailableZoneIds()) {
            System.out.println(zone);
        }
    }

    /**
     * java.time.temporal.UnsupportedTemporalTypeException: Unsupported field: Year
     * To format an Instant a time-zone is required.
     * https://stackoverflow.com/questions/40211892/unsupported-field-year-when-formatting-an-instant-to-date-iso?noredirect=1&lq=1
     * http://www.arccode.net/java-date.html
     */
    @Test
    public void format() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Etc/GMT-8"));
        String format = dateTimeFormatter.format(new Date().toInstant());
        System.out.println(format);//2018-03-20T20:37:29.464+08:00

        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ").withLocale(Locale.SIMPLIFIED_CHINESE).withZone(ZoneId.of("Etc/GMT-8"));
        format = dateTimeFormatter.format(new Date().toInstant());
        System.out.println(format);//2018-03-20T20:38:52+0800
    }


    @Test
    public void testMax() {
        LocalDateTime todayStart = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);//当天零点
        log.info("{}", todayStart.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        LocalDateTime todayEnd = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);//当天23:59:59
        log.info("{}", todayEnd.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }


}
