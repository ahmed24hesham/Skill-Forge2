package BackEnd;

public class Certificate {
    private String certificateId;
    private String studentId;
    private String courseId;
    private String issueDate;

    public Certificate(String certificateId, String studentId,String courseId, String issueDate) {
        this.certificateId = certificateId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.issueDate = issueDate;
    }
    public String getCertificateId() {
        return certificateId;
    }
    public String getStudentId() {
        return studentId;
    }
    public String getCourseId() { 
        return courseId; 
    }
    public String getIssueDate() {
        return issueDate;
    }
}
