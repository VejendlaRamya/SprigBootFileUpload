package com.portal.app.repository;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.portal.app.model.FileDao;

@Repository
public interface FileRepository extends JpaRepository<FileDao, Integer> {

	Stream<FileDao> findAllByUploadBy(String uploadedBy);

	@Query(value = "select * from file_dao", nativeQuery = true)
	Stream<FileDao> findByAll();

	@Query(value = "select * from file_dao where signed_status=0", nativeQuery = true)
	Stream<FileDao> findAllBySignedStatusAndUploadedBY();

	@Query(value = "select * from file_dao where signed_status=1 and upload_by =:uploadedBy", nativeQuery = true)
	Stream<FileDao> findAllByUploadByAndSignedStatus(@Param(value = "uploadedBy") String uploadedBy);

	@Query(value = "select * from file_dao where signed_status=0 and upload_by =:uploadedBy", nativeQuery = true)
	Stream<FileDao> findAllByUploadedByAndSignedStatus(@Param(value = "uploadedBy") String uploadedBy);

	@Query(value = "select * from file_dao where signed_status=0 and filename=:fileName", nativeQuery = true)
	FileDao findByFileNameAndSignStatus(@Param(value = "fileName") String fileName);

	@Transactional
	@Modifying
	@Query(value = "update  file_dao set status=:status  where id=:id", nativeQuery = true)
	void updateFileByStatus(@Param(value = "id") Integer id, @Param(value = "status") String status);

	@Transactional
	@Modifying
	@Query(value = "update  file_dao set commetns=:comments  where id=:id", nativeQuery = true)
	void updateFileByCOmments(@Param(value = "id") Integer id, @Param(value = "comments") String comments);

}
