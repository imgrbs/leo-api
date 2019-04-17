
CREATE TABLE `applicant_matches` (
     `id` bigint(20) NOT NULL,
     `applicant_id` bigint(20) NOT NULL,
     `match_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

INSERT into `applicant_matches` values (1,1,1);
INSERT into `applicant_matches` values (2,2,1);

CREATE TABLE `applicant_rankings` (
      `id` bigint(20) NOT NULL,
      `sequence` int(11) NOT NULL,
      `match_id` bigint(20) DEFAULT NULL,
      `applicant_match_id` bigint(20) DEFAULT NULL,
      `position_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

INSERT into `applicant_rankings` values (1,1,1,1,1);
INSERT into `applicant_rankings` values (2,1,1,2,1);

CREATE TABLE `matches` (
       `id` bigint(20) NOT NULL,
       `name` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

INSERT into `matches` values (1,'SIT Career day');
INSERT into `matches` values (2,'Engineer Career day');

CREATE TABLE `positions` (
         `id` bigint(20) NOT NULL,
         `capacity` int(11) NOT NULL,
         `name` varchar(255) DEFAULT NULL,
         `recruiter_match_id` bigint(20) DEFAULT NULL,
         `match_id` bigint(20) DEFAULT NULL,
         `money` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

INSERT into `positions` values (1,1,'Software Engineer',1,1,'฿10,000-฿15,000'),(2,1,'Developer',1,1,'฿10,000-฿15,000');

CREATE TABLE `recruiter_matches` (
     `id` bigint(20) NOT NULL,
     `recruiter_id` bigint(20) NOT NULL,
     `match_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

INSERT into `recruiter_matches` values (1,1,1);


CREATE TABLE `recruiter_rankings` (
      `id` bigint(20) NOT NULL,
      `sequence` int(11) NOT NULL,
      `match_id` bigint(20) DEFAULT NULL,
      `applicant_match_id` bigint(20) DEFAULT NULL,
      `position_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;