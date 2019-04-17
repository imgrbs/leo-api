ALTER TABLE `applicant_matches`
    ADD PRIMARY KEY (`id`),
    ADD KEY `FKmq6ti2x3cr6yekbxo2rxf1coa` (`match_id`);

--
-- Indexes for table `applicant_rankings`
--
ALTER TABLE `applicant_rankings`
    ADD PRIMARY KEY (`id`),
    ADD KEY `FK6obavefet84r86hp8fh8ki4vi` (`match_id`),
    ADD KEY `FKlhuocs4dlhulw645u7l4nne2t` (`applicant_match_id`),
    ADD KEY `FKax70rgyesgxkf8fjwu8tub8uf` (`position_id`);

--
-- Indexes for table `matches`
--
ALTER TABLE `matches`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `positions`
--
ALTER TABLE `positions`
    ADD PRIMARY KEY (`id`),
    ADD KEY `FKqygl7i4slg8bypjqgchpbud3b` (`match_id`),
    ADD KEY `FKkru74vep3ko68i05y8hqfj09j` (`recruiter_match_id`);

--
-- Indexes for table `recruiter_matches`
--
ALTER TABLE `recruiter_matches`
    ADD PRIMARY KEY (`id`),
    ADD KEY `FKdb2gxu61p0mmqchyj3d1yjb7f` (`match_id`);

--
-- Indexes for table `recruiter_rankings`
--
ALTER TABLE `recruiter_rankings`
    ADD PRIMARY KEY (`id`),
    ADD KEY `FK4s2phvqgwpr3kxep86uw8ob6a` (`match_id`),
    ADD KEY `FKe0wvgtbptb4xlcnieh6vfuybm` (`applicant_match_id`),
    ADD KEY `FKip4g2xi5ma9qcc4by8v2lgovk` (`position_id`);


ALTER TABLE `applicant_matches`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `applicant_rankings`
--
ALTER TABLE `applicant_rankings`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `matches`
--
ALTER TABLE `matches`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `positions`
--
ALTER TABLE `positions`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `recruiter_matches`
--
ALTER TABLE `recruiter_matches`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `recruiter_rankings`
--
ALTER TABLE `recruiter_rankings`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
COMMIT;