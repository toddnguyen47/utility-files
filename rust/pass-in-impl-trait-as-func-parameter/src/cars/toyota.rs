use super::icar::ICar;

pub struct Toyota;

impl Toyota {
    #[allow(dead_code)]
    pub fn new() -> Self {
        Self
    }
}

impl ICar for Toyota {
    fn get_make(&self) -> String {
        "Toyota".to_string()
    }
}
