package com.study.board.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long>{
	// Entity클래스와 Repository 인터페이스는 꼭 같은 패키지에 있어야 함!!
	// JpaRepository를 상속받을 때 엔티티 클래스의 타입과 해당 PK의 해당하는 자료형 타입을 선언
	
}
