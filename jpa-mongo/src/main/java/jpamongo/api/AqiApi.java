package jpamongo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jpamongo.dao.Pm25inService;
import jpamongo.model.common.Aqi;
import jpamongo.model.common.Code;
import jpamongo.model.common.Info;

@Controller
@RequestMapping(value = "/aqi", produces = {MediaType.APPLICATION_JSON_VALUE})
@Api("API for AQI data")
public class AqiApi {
	@Autowired
	private Pm25inService pm25inService;
	
	@ApiOperation(value = "AQI pm25.in 信息查询", notes = "", response = Aqi.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "获取成功", response = Aqi.class),
        @ApiResponse(code = 400, message = "获取失败", response = Info.class) })
	@RequestMapping(value = "/pm25in/{city}", method = RequestMethod.GET)
	public ResponseEntity<?> fetchPm25inAqi(@PathVariable("city") String city) {
		Aqi ret = pm25inService.fetchPm25BasicInfo(city);
		if (Info.getCode().equals(Code.ok)) {
			return new ResponseEntity<Aqi>(ret, HttpStatus.OK);
		}
		return new ResponseEntity<Info>(new Info(), HttpStatus.BAD_REQUEST);
	}
}
