package com.rays.ctl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.dto.CollegeDTO;
import com.rays.form.CollegeForm;
import com.rays.service.CollegeService;

@RestController
@RequestMapping(value = "College")
public class CollegeCtl extends BaseCtl {

	@Autowired
	public CollegeService collegeService;

	@PostMapping("save")
	public ORSResponse save(@RequestBody @Valid CollegeForm form, BindingResult bindingResult) {

		ORSResponse res = validate(bindingResult);

		if (!res.isSuccess()) {
			return res;
		}

		CollegeDTO dto = (CollegeDTO) form.getDto();

		if (dto.getId() != null && dto.getId() > 0) {
			collegeService.update(dto);
			res.addData(dto.getId());
			res.addMessage("College Updated Successfully..!!");
		} else {
			long pk = collegeService.add(dto);
			res.addData(pk);
			res.addMessage("College added Successfully..!!");
		}
		return res;
	}

	@GetMapping("get/{id}")
	public ORSResponse get(@PathVariable long id) {

		ORSResponse res = new ORSResponse();

		CollegeDTO dto = collegeService.findById(id);

		res.addData(dto);

		return res;
	}

	@GetMapping("delete/{ids}")
	public ORSResponse delete(@PathVariable long[] ids) {

		ORSResponse res = new ORSResponse();

		for (long id : ids) {
			collegeService.delete(id);
		}

		res.addMessage("College deleted successfully");

		return res;
	}

	@PostMapping("search/{pageNo}")
	public ORSResponse search(@RequestBody CollegeForm form, @PathVariable int pageNo) {

		ORSResponse res = new ORSResponse();

		CollegeDTO dto = (CollegeDTO) form.getDto();

		List list = collegeService.search(dto, pageNo, 5);

		if (list.size() == 0) {
			res.addMessage("Result not found...!!!");
		} else {
			res.addData(list);
		}
		return res;
	}
}
