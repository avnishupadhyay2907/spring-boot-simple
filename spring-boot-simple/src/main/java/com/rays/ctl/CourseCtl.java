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
import com.rays.dto.CourseDTO;
import com.rays.dto.CourseDTO;
import com.rays.form.CourseForm;
import com.rays.service.CourseService;
import com.rays.service.CourseService;

@RestController
@RequestMapping(value = "Course")
public class CourseCtl extends BaseCtl {

	@Autowired
	public CourseService courseService;

	@PostMapping("save")
	public ORSResponse save(@RequestBody @Valid CourseForm form, BindingResult bindingResult) {

		ORSResponse res = validate(bindingResult);

		if (!res.isSuccess()) {
			return res;
		}

		CourseDTO dto = (CourseDTO) form.getDto();

		if (dto.getId() != null && dto.getId() > 0) {
			courseService.update(dto);
			res.addData(dto.getId());
			res.addMessage("Course Updated Successfully..!!");
		} else {
			long pk = courseService.add(dto);
			res.addData(pk);
			res.addMessage("Course added Successfully..!!");
		}
		return res;
	}

	@GetMapping("get/{id}")
	public ORSResponse get(@PathVariable long id) {

		ORSResponse res = new ORSResponse();

		CourseDTO dto = courseService.findById(id);

		res.addData(dto);

		return res;
	}

	@GetMapping("delete/{ids}")
	public ORSResponse delete(@PathVariable long[] ids) {

		ORSResponse res = new ORSResponse();

		for (long id : ids) {
			courseService.delete(id);
		}

		res.addMessage("Course deleted successfully");

		return res;
	}

	@PostMapping("search/{pageNo}")
	public ORSResponse search(@RequestBody CourseForm form, @PathVariable int pageNo) {

		ORSResponse res = new ORSResponse();

		CourseDTO dto = (CourseDTO) form.getDto();

		List list = courseService.search(dto, pageNo, 5);

		if (list.size() == 0) {
			res.addMessage("Result not found...!!!");
		} else {
			res.addData(list);
		}
		return res;
	}
}