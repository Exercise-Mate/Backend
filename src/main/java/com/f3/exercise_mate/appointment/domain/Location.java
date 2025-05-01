package com.f3.exercise_mate.appointment.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
*   kakao API return 값
*   "address_name": "서울 성동구 성수동1가 678-1",
    "category_group_code": "",
    "category_group_name": "",
    "category_name": "여행 > 공원 > 도시근린공원",
    "distance": "28619",
    "id": "11331488",
    "phone": "02-460-2905",
    "place_name": "서울숲",
    "place_url": "http://place.map.kakao.com/11331488",
    "road_address_name": "",
    "x": "127.037617759165",
    "y": "37.5443222301513"
* */
@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Location {
    private String address;
    private String name;
    private String url;
    private Double x;
    private Double y;


    public Location(String address, String name, String url, double x, double y) {
        this.address = address;
        this.name = name;
        this.url = url;
        this.x = x;
        this.y = y;
    }

    public Location(String address, String name, String url) {
        this.address = address;
        this.name = name;
        this.url = url;
        this.x = 127.0;
        this.y = 36.0;
    }


    /* kakao api로 검색 후 그 값을 받아오기 때문에 검증로직 미작성 */

}
