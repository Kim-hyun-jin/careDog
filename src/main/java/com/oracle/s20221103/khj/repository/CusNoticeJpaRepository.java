package com.oracle.s20221103.khj.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.oracle.s20221103.domain.CusNotice;

@EnableJpaRepositories
public interface CusNoticeJpaRepository extends JpaRepository<CusNotice, Long> {

	//Impl 을 해야함 => custom
	@Query("select n from CusNotice AS n WHERE n.title like concat('%',:keyword,'%')")
	List<CusNotice> findByKeyword(@Param("keyword") String keyword, Pageable pageable);
	
	Page<CusNotice> findAll(Pageable pageable);
	
	@Query(value = "INSERT INTO CUS_NOTICE (cus_no, id, title, content, regdate) VALUES (cus_notice_seq.nextval, 10, :title, :content , sysdate) ", nativeQuery = true)
	public int insCusNotice(@Param("title") String title,@Param("content") String content);

	@Query(value = "INSERT INTO IMAGE (table_type, cus_no, image_no, img_path, img_name ) VALUES ('CUS_NOTICE', :cus_no ,IMAGE_SEQ.nextval, :img_path, :img_name)", nativeQuery = true)
	public int insIMG(@Param("cus_no")Long cusNo, @Param("img_path")String uploadPath, @Param("img_name")  String fileName);
	
}
