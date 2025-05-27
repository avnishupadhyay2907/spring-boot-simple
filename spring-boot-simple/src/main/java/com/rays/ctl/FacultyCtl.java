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
import com.rays.dto.FacultyDTO;
import com.rays.form.FacultyForm;
import com.rays.service.FacultyService;

@RestController
@RequestMapping(value = "Faculty")
public class FacultyCtl extends BaseCtl{

	@Autowired
	public FacultyService facultyService;

	@PostMapping("save")
	public ORSResponse save(@RequestBody @Valid FacultyForm form, BindingResult bindingResult) {

		ORSResponse res = validate(bindingResult);

		if (!res.isSuccess()) {
			return res;
		}

		FacultyDTO dto = (FacultyDTO) form.getDto();

		if (dto.getId() != null && dto.getId() > 0) {
			facultyService.update(dto);
			res.addData(dto.getId());
			res.addMessage("Faculty Updated Successfully..!!");
		} else {
			long pk = facultyService.add(dto);
			res.addData(pk);
			res.addMessage("Faculty added Successfully..!!");
		}
		return res;
	}

	@GetMapping("get/{id}")
	public ORSResponse get(@PathVariable long id) {

		ORSResponse res = new ORSResponse();

		FacultyDTO dto = facultyService.findById(id);

		res.addData(dto);

		return res;
	}

	@GetMapping("delete/{ids}")
	public ORSResponse delete(@PathVariable long[] ids) {

		ORSResponse res = new ORSResponse();

		for (long id : ids) {
			facultyService.delete(id);
		}

		res.addMessage("Faculty deleted successfully");

		return res;
	}

	@PostMapping("search/{pageNo}")
	public ORSResponse search(@RequestBody FacultyForm form, @PathVariable int pageNo) {

		ORSResponse res = new ORSResponse();

		FacultyDTO dto = (FacultyDTO) form.getDto();

		List list = facultyService.search(dto, pageNo, 5);

		if (list.size() == 0) {
			res.addMessage("Result not found...!!!");
		} else {
			res.addData(list);
		}
		return res;
	}

}
