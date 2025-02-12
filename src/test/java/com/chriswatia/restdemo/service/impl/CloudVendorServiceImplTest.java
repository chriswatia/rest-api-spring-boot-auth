package com.chriswatia.restdemo.service.impl;

import com.chriswatia.restdemo.model.CloudVendor;
import com.chriswatia.restdemo.repository.CloudVendorRepository;
import com.chriswatia.restdemo.service.CloudVendorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;


class CloudVendorServiceImplTest {
    // Mock the CloudVendorRepository
    @Mock
    private CloudVendorRepository cloudVendorRepository;
    private CloudVendorService cloudVendorService;

    // To close all unwanted connections
    AutoCloseable autoCloseable;
    CloudVendor cloudVendor;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        // Init the CloudVendorService
        cloudVendorService = new CloudVendorServiceImpl(cloudVendorRepository);
        cloudVendor = new CloudVendor(1L, "AWS", "USA", "12345678");
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testCreateCloudVendor() {
        // Prepare the mock
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);

        // Act
        when(cloudVendorRepository.save(cloudVendor)).thenReturn(cloudVendor);

        // Assert
        assertThat(cloudVendorService.createCloudVendor(cloudVendor)).isEqualTo("Success");
    }

    @Test
    void testUpdateCloudVendor() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);

        // Act
        when(cloudVendorRepository.save(cloudVendor)).thenReturn(cloudVendor);

        // Assert
        assertThat(cloudVendorService.updateCloudVendor(cloudVendor)).isEqualTo("Success");
    }

    @Test
    void testDeleteCloudVendor() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class, Mockito.CALLS_REAL_METHODS);

        // Act
        doAnswer(Answers.CALLS_REAL_METHODS).when(cloudVendorRepository).deleteById(any());

        // Assert
        assertThat(cloudVendorService.deleteCloudVendor("1")).isEqualTo("Success");
    }

    @Test
    void testGetCloudVendor() {
        // Prepare the mock
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);

        // Act
        when(cloudVendorRepository.findById("1")).thenReturn(Optional.of(cloudVendor));

        // Assert
        assertThat(cloudVendorService.getCloudVendor("1").getVendorName()).isEqualTo(cloudVendor.getVendorName());
    }

    @Test
    void testGetAllCloudVendors() {
        // Prepare the mock
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);

        // Act
        when(cloudVendorRepository.findAll()).thenReturn(List.of(cloudVendor));

        // Assert
        assertThat(cloudVendorService.getAllCloudVendors().size()).isEqualTo(1);
    }

    @Test
    void testGetByVendorName() {
        // Prepare the mock
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);

        // Act
        when(cloudVendorRepository.findByVendorName("AWS")).thenReturn(List.of(cloudVendor));

        // Assert
        assertThat(cloudVendorService.getByVendorName("AWS").getFirst().getVendorId()).isEqualTo(cloudVendor.getVendorId());
    }
}