package com.chriswatia.restdemo.repository;

import com.chriswatia.restdemo.model.CloudVendor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CloudVendorRepositoryTests {
    @Autowired
    private CloudVendorRepository cloudVendorRepository;
    CloudVendor cloudVendor;

    @BeforeEach
    public void setUp() {
        cloudVendor = new CloudVendor(1L, "AWS", "Seattle", "1234567890");
        cloudVendorRepository.save(cloudVendor);
    }

    // Test case SUCCESS
    @Test
    public void testFindByVendorName_Found() {
      List<CloudVendor> cloudVendorList = cloudVendorRepository.findByVendorName("AWS");

      assertThat(cloudVendorList.get(0).getVendorId()).isEqualTo(cloudVendor.getVendorId());
      assertThat(cloudVendorList.get(0).getVendorAddress()).isEqualTo(cloudVendor.getVendorAddress());
    }

    // Test case FAILURE
    @Test
    public void testFindByVendorName_NotFound() {
      List<CloudVendor> cloudVendorList = cloudVendorRepository.findByVendorName("Azure");

      assertThat(cloudVendorList.isEmpty()).isTrue();
    }
    @AfterEach
    public void tearDown() {
        cloudVendor = null;
        cloudVendorRepository.deleteAll();
    }
}
