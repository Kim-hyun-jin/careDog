package com.oracle.s20221103.khj.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.s20221103.domain.CusNotice;

import com.oracle.s20221103.khj.dto.PageRequestDTO;
import com.oracle.s20221103.khj.dto.PageResponseDTO;

import lombok.RequiredArgsConstructor;

@Repository
@Transactional
@RequiredArgsConstructor
public class CusNoticeRepositoryImpl implements CusNoticeRepository {

	@Autowired
	private final EntityManager em;

	@Override
	public Long getTotal() {
		Long total = (Long) em.createQuery("select count(n) from CusNotice as n")
				.getSingleResult();
		return total;
	}

	@Override
	public PageResponseDTO<CusNotice> findAll(PageRequestDTO pageRequestDTO) {

		int pageSize = 10;
		int offset = (pageRequestDTO.getPage() - 1) * pageSize;
		int maxResult = pageRequestDTO.getSize();
		String sorting = pageRequestDTO.getSorting();
		List<CusNotice> list = null;

		if (sorting.equals("desc")) {
			list = em.createQuery("select n from CusNotice as n ORDER BY Regdate desc", CusNotice.class)
					.setFirstResult(offset)
					.setMaxResults(maxResult)
					.getResultList();
		}
		if (sorting.equals("asc")) {
			list = em.createQuery("select n from CusNotice as n ORDER BY Regdate", CusNotice.class)
					.setFirstResult(offset)
					.setMaxResults(maxResult)
					.getResultList();
		}
		System.out.println("findAll list size: " + list.size());
		PageResponseDTO<CusNotice> dto = new PageResponseDTO<CusNotice>(getTotal(), pageRequestDTO, list);

		return dto;
	}

	@Override
	public PageResponseDTO<CusNotice> findBytitleOrderByRegdate(PageRequestDTO pageRequestDTO) {

		int pageSize = 10;
		int offset = (pageRequestDTO.getPage() - 1) * pageSize;
		int maxResult = pageRequestDTO.getSize();
		String title = pageRequestDTO.getKeyword();
		String sort = pageRequestDTO.getSorting(); // "DESC , ASC"는 쿼리파라미터로 꽂을수 없음 . + 로 직접 더해줘야함

		System.out.println("CusNoticeRepositoryImpl findBytitleOrderByRegdateDesc start");
		String titlek = '%' + title + '%';
		List<CusNotice> list = em
				.createQuery("select n from CusNotice AS n WHERE n.title like :title ORDER BY Regdate " + sort,
						CusNotice.class)
				.setParameter("title", titlek)
				.setFirstResult(offset)
				.setMaxResults(maxResult)
				.getResultList();

		System.out.println("search list size: " + list.size());
		PageResponseDTO<CusNotice> dto = new PageResponseDTO<CusNotice>(byTitleCount(pageRequestDTO), pageRequestDTO,
				list);
		return dto;
	}

	@Override
	public Long byTitleCount(PageRequestDTO pageRequestDTO) {
		String title = pageRequestDTO.getKeyword();
		String sort = pageRequestDTO.getSorting(); // "DESC , ASC"는 쿼리파라미터로 꽂을수 없음 . + 로 직접 더해줘야함
		System.out.println("CusNoticeRepositoryImpl count start");
		String titlek = '%' + title + '%';
		Long count = em
				.createQuery("select count(n) from CusNotice AS n WHERE n.title like :title ORDER BY Regdate " + sort,
						Long.class)
				.setParameter("title", titlek)
				.getSingleResult();

		return count;
	}

	@Override
	public PageResponseDTO<CusNotice> findByContentOrderByRegdate(PageRequestDTO pageRequestDTO) {

		int pageSize = 10;
		int offset = (pageRequestDTO.getPage() - 1) * pageSize;
		int maxResult = pageRequestDTO.getSize();
		String content = pageRequestDTO.getKeyword();
		String sort = pageRequestDTO.getSorting();
		String contentk = '%' + content + '%';

		List<CusNotice> list = em
				.createQuery("select n from CusNotice AS n WHERE n.content like :content ORDER BY Regdate " + sort,
						CusNotice.class)
				.setParameter("content", contentk)
				.setFirstResult(offset)
				.setMaxResults(maxResult)
				.getResultList();
		PageResponseDTO<CusNotice> dto = new PageResponseDTO<CusNotice>(byContentCount(pageRequestDTO), pageRequestDTO,
				list);
		return dto;
	}

	@Override
	public Long byContentCount(PageRequestDTO pageRequestDTO) {
		String content = pageRequestDTO.getKeyword();
		String sort = pageRequestDTO.getSorting();
		String contentk = '%' + content + '%';

		Long total = em
				.createQuery(
						"select count(n) from CusNotice AS n WHERE n.content like :content ORDER BY Regdate " + sort,
						Long.class)
				.setParameter("content", contentk)
				.getSingleResult();
		return total;
	}

	@Override
	public PageResponseDTO<CusNotice> findByAllOrderByRegdate(PageRequestDTO pageRequestDTO) {

		int pageSize = 10;
		int offset = (pageRequestDTO.getPage() - 1) * pageSize;
		int maxResult = pageRequestDTO.getSize();
		String titleContent = pageRequestDTO.getKeyword();
		String sort = pageRequestDTO.getSorting();
		String titleContentk = '%' + titleContent + '%';

		List<CusNotice> list = em.createQuery(
				"select n from CusNotice AS n WHERE (n.title like :keyword OR n.content like :keyword) ORDER BY Regdate "
						+ sort,
				CusNotice.class)
				.setParameter("keyword", titleContentk)
				.setFirstResult(offset)
				.setMaxResults(maxResult)
				.getResultList();

		PageResponseDTO<CusNotice> dto = new PageResponseDTO<CusNotice>(byAllCount(pageRequestDTO), pageRequestDTO,
				list);

		return dto;

	}

	@Override
	public Long byAllCount(PageRequestDTO pageRequestDTO) {
		String titleContent = pageRequestDTO.getKeyword();
		String sort = pageRequestDTO.getSorting();
		String titleContentk = '%' + titleContent + '%';

		Long total = em.createQuery(
				"select count(n) from CusNotice AS n WHERE (n.title like :keyword OR n.content like :keyword) ORDER BY Regdate "
						+ sort,
				Long.class)
				.setParameter("keyword", titleContentk)
				.getSingleResult();
		return total;
	}

}
