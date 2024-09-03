package ru.hogwarts.school.model;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;
import java.util.Objects;

@Entity
public class Avatar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filePath;
    private long fileSize;
    private String mediaType;
    @Lob
    private byte[] preview;
    @OneToOne
    private Student student;

    @Value("${path.to.avatars.folder}")
    private String avatarsDir;

    public Avatar() {
    }

    public Avatar(Long id, String filePath, long fileSize, String mediaType, byte[] preview, Student student, String avatarsDir) {
        this.id = id;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.mediaType = mediaType;
        this.preview = preview;
        this.student = student;
        this.avatarsDir = avatarsDir;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public byte[] getPreview() {
        return preview;
    }

    public void setPreview(byte[] preview) {
        this.preview = preview;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getAvatarsDir() {
        return avatarsDir;
    }

    public void setAvatarsDir(String avatarsDir) {
        this.avatarsDir = avatarsDir;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avatar avatar = (Avatar) o;
        return fileSize == avatar.fileSize && Objects.equals(id, avatar.id) && Objects.equals(filePath, avatar.filePath) && Objects.equals(mediaType, avatar.mediaType) && Arrays.equals(preview, avatar.preview) && Objects.equals(student, avatar.student) && Objects.equals(avatarsDir, avatar.avatarsDir);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, filePath, fileSize, mediaType, student, avatarsDir);
        result = 31 * result + Arrays.hashCode(preview);
        return result;
    }

    @Override
    public String toString() {
        return "Avatar{" +
                "id=" + id +
                ", filePath='" + filePath + '\'' +
                ", fileSize=" + fileSize +
                ", mediaType='" + mediaType + '\'' +
                ", data=" + Arrays.toString(preview) +
                ", student=" + student +
                ", avatarsDir='" + avatarsDir + '\'' +
                '}';
    }
}
